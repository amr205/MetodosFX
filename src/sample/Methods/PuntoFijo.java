package sample.Methods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Methods.MethodModel.BiseccionModel;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PuntoFijo extends tableMethod {
    @Override
    public void initializeColumns(TableView table) {
        table.getColumns().clear();
        TableColumn column = new TableColumn("n");
        column.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("n"));

        TableColumn column1 = new TableColumn("a");
        column1.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("a"));

        TableColumn column2 = new TableColumn("b");
        column2.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("b"));

        TableColumn column3 = new TableColumn("fa");
        column3.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("fa"));

        TableColumn column4 = new TableColumn("fb");
        column4.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("fb"));


        TableColumn column5 = new TableColumn("xr");
        column5.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("xr"));

        TableColumn column6 = new TableColumn("fxr");
        column6.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("fxr"));

        TableColumn column7 = new TableColumn("error");
        column7.setCellValueFactory(new PropertyValueFactory<BiseccionModel,String>("error"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3,column4, column5, column6, column7);
    }

    @Override
    public float doMethod(TableView table, float aInicial, float bInicial, float errorP, String ecuacion, ArrayList<Object> list) {
        final ObservableList<BiseccionModel> data = FXCollections.observableArrayList();

        TextField field = (TextField)list.get(0);
        System.out.println(field.getText());

        BigDecimal a, b, fa, fb, fxr, xr, xrAnt, error, resultado;

        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);

        return  error.floatValue();
    }

    @Override
    public String toString(){
        //return "Bisección";
        return "Punto fijo";
    }
}
