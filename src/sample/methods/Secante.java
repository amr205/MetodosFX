package sample.methods;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import sample.Utilities.ObservableResourceFactory;
import sample.methods.model.SecanteModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Secante extends TableMethod {
    public Secante(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initializeColumns(TableView table) {
        table.getColumns().clear();
        TableColumn column = new TableColumn("n");
        column.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("n"));

        TableColumn column1 = new TableColumn("xi-1");
        column1.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("x0"));

        TableColumn column2 = new TableColumn("xi");
        column2.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("xi"));

        TableColumn column3 = new TableColumn("f(xi-1)");
        column3.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("fx0"));

        TableColumn column4 = new TableColumn("f(xi)");
        column4.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("fxi"));

        TableColumn column5 = new TableColumn("xi+1");
        column5.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("x2"));

        TableColumn column6 = new TableColumn("error");
        column6.setCellValueFactory(new PropertyValueFactory<SecanteModel,String>("error"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3,column4, column5, column6);
    }

    @Override
    public float doMethod(TableView table, float aInicial, float bInicial, float errorP, String ecuacion, ArrayList<Object> list) {
        final ObservableList<SecanteModel> data = FXCollections.observableArrayList();

        BigDecimal x0, xi, fx0, fxi, x2, x2Ant, error, resultado;
        Double x2Double;
        int n;
        n=1;
        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);
        x0=new BigDecimal(aInicial).setScale(6, BigDecimal.ROUND_HALF_UP);
        xi=new BigDecimal(bInicial).setScale(6, BigDecimal.ROUND_HALF_UP);

        x2 = new BigDecimal(20).setScale(6,BigDecimal.ROUND_HALF_UP);
        do{

            double fx0Double = new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",x0.doubleValue()).evaluate();
            double fxiDouble = new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",xi.doubleValue()).evaluate();

            fx0 = new BigDecimal(fx0Double).setScale(6, BigDecimal.ROUND_HALF_UP);
            fxi = new BigDecimal(fxiDouble).setScale(6, BigDecimal.ROUND_HALF_UP);

            x2Ant = x2;

            x2Double = xi.doubleValue()-((fxi.doubleValue()*(x0.doubleValue()-xi.doubleValue()))/(fx0.doubleValue()-fxi.doubleValue()));
            x2 =  new BigDecimal(x2Double).setScale(6, BigDecimal.ROUND_HALF_UP);


            if(n!=1) {
                //error = ((x2.subtract(xrAnt))).divide(x2,6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(6, BigDecimal.ROUND_HALF_UP)).abs();

                double errorDouble = Math.abs((x2.doubleValue()-x2Ant.doubleValue())/x2.doubleValue()*100);
                error = new BigDecimal(errorDouble).setScale(6,BigDecimal.ROUND_HALF_UP);

                data.add(new SecanteModel(Integer.toString(n),String.format("%.6f", x0),String.format("%.6f", xi),String.format("%.6f", fx0),
                        String.format("%.6f", fxi), String.format("%.6f", x2), String.format("%.6f", error)));
            }
            else
            {
                data.add(new SecanteModel(Integer.toString(n),String.format("%.6f", x0),String.format("%.6f", xi),String.format("%.6f", fx0),
                        String.format("%.6f", fxi), String.format("%.6f", x2), "--------"));
            }

            x0 = xi;
            xi = x2;


            n++;
        }while(error.floatValue()>errorP);

        table.setItems(data);

        resultado=x2;
        return  resultado.floatValue();
    }

    @Override
    public String toString(){
        //return "Bisección";
        return  RESOURCE_FACTORY.getResources().getString("SecanteMethod");
    }

    @Override
    public void showDescription(){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descSecanteHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descSecanteDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }
}