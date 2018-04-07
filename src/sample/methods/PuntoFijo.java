package sample.methods;

import sample.Utilities.ObservableResourceFactory;
import sample.methods.model.PuntoFijoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PuntoFijo extends TableMethod{

    public PuntoFijo(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initializeColumns(TableView table) {
        System.out.println("inicialize columnas");
        table.getColumns().clear();
        TableColumn column = new TableColumn("n");
        column.setCellValueFactory(new PropertyValueFactory<PuntoFijoModel,String>("n"));

        TableColumn column1 = new TableColumn("x0");
        column1.setCellValueFactory(new PropertyValueFactory<PuntoFijoModel,String>("x0"));

        TableColumn column2 = new TableColumn("x1");
        column2.setCellValueFactory(new PropertyValueFactory<PuntoFijoModel,String>("x1"));

        TableColumn column3 = new TableColumn("error");
        column3.setCellValueFactory(new PropertyValueFactory<PuntoFijoModel,String>("error"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3);
    }

    @Override
    public float doMethod(TableView table, float aInicial, float bInicial, float errorP, String ecuacion, ArrayList<Object> list) {
        final ObservableList<PuntoFijoModel> data = FXCollections.observableArrayList();

        TextField xField = (TextField)list.get(0);
        Expression gx = new ExpressionBuilder(xField.getText()).variable("x").build();

        BigDecimal x0, x1, error, resultado;

        int n;
        n=1;
        x0=new BigDecimal(aInicial).setScale(6, BigDecimal.ROUND_HALF_UP);
        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);

        System.out.println("inicie metodo");


        do{

            double x1Double = gx.setVariable("x",x0.doubleValue()).evaluate();

            x1 = new BigDecimal(x1Double).setScale(6, BigDecimal.ROUND_HALF_UP);


            if(n!=1) {
                //error = ((xr.subtract(xrAnt))).divide(xr,6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(6, BigDecimal.ROUND_HALF_UP)).abs();

                double errorDouble = Math.abs((x1.doubleValue()-x0.doubleValue())/x1.doubleValue()*100);
                error = new BigDecimal(errorDouble).setScale(6,BigDecimal.ROUND_HALF_UP);

                data.add(new PuntoFijoModel(Integer.toString(n),String.format("%.6f", x0),String.format("%.6f", x1), String.format("%.6f", error)));
            }
            else
            {
                data.add(new PuntoFijoModel(Integer.toString(n),String.format("%.6f", x0),String.format("%.6f", x1), "------"));

            }

            x0 = x1;
            n++;
        }while(error.floatValue()>errorP);

        table.setItems(data);

        resultado=x1;


        System.out.println("termine metodo");
        return  resultado.floatValue();
    }

    @Override
    public void initializeOptional(VBox optionalFields, ArrayList<Object> optionalObjects) {
        HBox hBox = new HBox();
        hBox.setSpacing(3);
        hBox.getChildren().add(new Label("x = " ));
        TextField textField = new TextField();
        optionalObjects.add(textField);
        hBox.getChildren().add(textField);
        optionalFields.getChildren().add(hBox);
    }

    @Override
    public String toString(){
        //return "Bisección";
        return  RESOURCE_FACTORY.getResources().getString("pointMethod");
    }

    @Override
    public boolean validateOptional(ObservableResourceFactory RESOURCE_FACTORY, ArrayList<Object> optionalObjects) {
        TextField xField = (TextField)optionalObjects.get(0);
        try{
            Expression expression = new ExpressionBuilder(xField.getText()).variable("x").build();
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

    @Override
    public void showDescription(){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descFixedPointHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descFixedPointDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }
}

