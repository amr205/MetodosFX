package sample.Methods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Methods.MethodModel.BiseccionModel;
import sample.Methods.MethodModel.Person;

public class Biseccion extends tableMethod {
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
    public float doMethod(TableView table, float aInicial, float bInicial, String ecuacion) {
        final ObservableList<BiseccionModel> data = FXCollections.observableArrayList();

        float a, b, fa, fb, fxr, xr, xrAnt, error, resultado;
        int n;
        n=1;
        error = 20;
        a=aInicial;
        b=bInicial;
        xr = (a+b)/2;

        do{

            fa = (float)(new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",a).evaluate());
            fb = (float)(new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",b).evaluate());
            xrAnt = xr;
            xr = (a+b)/2;

            fxr = (float)(new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",xr).evaluate());

            if(fxr*fa==0)
                error=0;
            else if(fxr*fa>0)
                a=xr;
            else
                b=xr;

            if(n!=1) {
                error = Math.abs((xr - xrAnt) / xr * 100);
                data.add(new BiseccionModel(Integer.toString(n),String.format("%.6f", a),String.format("%.6f", b),String.format("%.6f", fa),
                        String.format("%.6f", fb), String.format("%.6f", xr), String.format("%.6f", fxr), String.format("%.6f", error)));
            }
            else
            {
                data.add(new BiseccionModel(Integer.toString(n),String.format("%.6f", a),String.format("%.6f", b),String.format("%.6f", fa),
                        String.format("%.6f", fb), String.format("%.6f", xr), String.format("%.6f", fxr), "--------"));
            }

            n++;
        }while(error>0.01);

        table.setItems(data);

        resultado=xr;
        return  resultado;
    }

    @Override
    public String toString(){
        //return "Bisección";
        return "Bisection";
    }
}
