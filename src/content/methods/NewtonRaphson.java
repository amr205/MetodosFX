package content.methods;

import content.Utilities.ObservableResourceFactory;
import content.methods.TableMethod;
import content.methods.model.NewtonRaphsonModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class NewtonRaphson extends TableMethod{

    public NewtonRaphson(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initializeColumns(TableView table) {
        System.out.println("inicialize columnas");
        table.getColumns().clear();
        TableColumn column = new TableColumn("n");
        column.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("n"));

        TableColumn column1 = new TableColumn("xi");
        column1.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("xi"));

        TableColumn column2 = new TableColumn("f(xi)");
        column2.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("fxi"));

        TableColumn column3 = new TableColumn("f'(xi)");
        column3.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("dfxi"));

        TableColumn column4 = new TableColumn("x i+1");
        column4.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("xi2"));

        TableColumn column5 = new TableColumn("error");
        column5.setCellValueFactory(new PropertyValueFactory<NewtonRaphsonModel,String>("error"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3,column4, column5);
    }

    @Override
    public float doMethod(TableView table, float aInicial, float bInicial, float errorP, String ecuacion, ArrayList<Object> list) {
        final ObservableList<NewtonRaphsonModel> data = FXCollections.observableArrayList();

        TextField dfxField = (TextField)list.get(0);
        Expression fx = new ExpressionBuilder(ecuacion).variable("x").build();
        Expression dfx = new ExpressionBuilder(dfxField.getText()).variable("x").build();

        BigDecimal xi, fxi, dfxi, xi2, error, resultado;

        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);
        int n;
        n=1;
        xi=new BigDecimal(aInicial).setScale(6, BigDecimal.ROUND_HALF_UP);
        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);

        System.out.println("inicie metodo");


        do{

            double fxiDouble = fx.setVariable("x",xi.doubleValue()).evaluate();
            double dfxiDouble = dfx.setVariable("x",xi.doubleValue()).evaluate();

            fxi = new BigDecimal(fxiDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            dfxi = new BigDecimal(dfxiDouble).setScale(6, BigDecimal.ROUND_HALF_UP);

            double xi2Double = xi.doubleValue() - (fxi.doubleValue()/dfxi.doubleValue());
            xi2 = new BigDecimal(xi2Double).setScale(6, BigDecimal.ROUND_HALF_UP);

            if(n!=1) {
                //error = ((xr.subtract(xrAnt))).divide(xr,6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(6, BigDecimal.ROUND_HALF_UP)).abs();

                double errorDouble = Math.abs((xi2.doubleValue()-xi.doubleValue())/xi2.doubleValue()*100);
                error = new BigDecimal(errorDouble).setScale(6,BigDecimal.ROUND_HALF_UP);

                data.add(new NewtonRaphsonModel(Integer.toString(n),String.format("%.6f", xi),String.format("%.6f", fxi),String.format("%.6f", dfxi),
                        String.format("%.6f", xi2), String.format("%.6f", error)));
            }
            else
            {
                data.add(new NewtonRaphsonModel(Integer.toString(n),String.format("%.6f", xi),String.format("%.6f", fxi),String.format("%.6f", dfxi),
                        String.format("%.6f", xi2), "-----------"));
            }

            xi = xi2;
            n++;
        }while(error.floatValue()>errorP);

        table.setItems(data);

        resultado=xi2;


        System.out.println("termine metodo");
        return  resultado.floatValue();
    }

    @Override
    public void initializeOptional(VBox optionalFields, ArrayList<Object> optionalObjects) {
        HBox hBox = new HBox();
        hBox.setSpacing(3);
        hBox.getChildren().add(new Label("f'(x)= " ));
        TextField textField = new TextField();
        optionalObjects.add(textField);
        hBox.getChildren().add(textField);
        optionalFields.getChildren().add(hBox);
    }

    @Override
    public String toString(){
        //return "Bisección";
        return  RESOURCE_FACTORY.getResources().getString("NewtonRaphsonMethod");
    }

    @Override
    public boolean validateOptional(ObservableResourceFactory RESOURCE_FACTORY, ArrayList<Object> optionalObjects) {
        TextField dfxField = (TextField)optionalObjects.get(0);
        try{
            Expression expression = new ExpressionBuilder(dfxField.getText()).variable("x").build();
            System.out.println("verifique validez");
            return true;
        }catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
            error.setHeaderText(RESOURCE_FACTORY.getResources().getString("equationDfxErrorHeader"));
            error.setContentText(RESOURCE_FACTORY.getResources().getString("equationErrorDescription"));
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();
            return false;
        }
    }
}

