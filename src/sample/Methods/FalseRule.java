package sample.Methods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Methods.MethodModel.BiseccionModel;
import sample.Methods.MethodModel.Person;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FalseRule extends tableMethod {
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

        BigDecimal a, b, fa, fb, fxr, xr, xrAnt, error, resultado;
        Double xrDoble;
        int n;
        n=1;
        error = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);
        a=new BigDecimal(aInicial).setScale(6, BigDecimal.ROUND_HALF_UP);
        b=new BigDecimal(bInicial).setScale(6, BigDecimal.ROUND_HALF_UP);

        xr = (a.add(b)).divide(new BigDecimal(2).setScale(6,BigDecimal.ROUND_HALF_UP));

        do{

            double faDouble = new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",a.doubleValue()).evaluate();
            double fbDouble = new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",b.doubleValue()).evaluate();

            fa = new BigDecimal(faDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            fb = new BigDecimal(fbDouble).setScale(6, BigDecimal.ROUND_HALF_UP);

            xrAnt = xr;

            xrDoble = b.doubleValue()-((fb.doubleValue()*(a.doubleValue()-b.doubleValue()))/(fa.doubleValue()-fb.doubleValue()));
            xr =  new BigDecimal(xrDoble).setScale(6, BigDecimal.ROUND_HALF_UP);

            double fxrDouble = new ExpressionBuilder(ecuacion).variable("x").build().
                    setVariable("x",xr.doubleValue()).evaluate();

            fxr = new BigDecimal(fxrDouble).setScale(6, BigDecimal.ROUND_HALF_UP);


            if(n!=1) {
                //error = ((xr.subtract(xrAnt))).divide(xr,6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100).setScale(6, BigDecimal.ROUND_HALF_UP)).abs();

                double errorDouble = Math.abs((xr.doubleValue()-xrAnt.doubleValue())/xr.doubleValue()*100);
                error = new BigDecimal(errorDouble).setScale(6,BigDecimal.ROUND_HALF_UP);

                data.add(new BiseccionModel(Integer.toString(n),String.format("%.6f", a),String.format("%.6f", b),String.format("%.6f", fa),
                        String.format("%.6f", fb), String.format("%.6f", xr), String.format("%.6f", fxr), String.format("%.6f", error)));
            }
            else
            {
                data.add(new BiseccionModel(Integer.toString(n),String.format("%.6f", a),String.format("%.6f", b),String.format("%.6f", fa),
                        String.format("%.6f", fb), String.format("%.6f", xr), String.format("%.6f", fxr), "--------"));
            }

            if(fxr.floatValue()*fa.floatValue()==0)
                error=new BigDecimal(0).setScale(6, BigDecimal.ROUND_HALF_UP);
            else if(fxr.floatValue()*fa.floatValue()>0)
                a=xr;
            else
                b=xr;



            n++;
        }while(error.floatValue()>errorP);

        table.setItems(data);

        resultado=xr;
        return  resultado.floatValue();
    }

    @Override
    public String toString(){
        //return "Bisección";
        return "False Rule";
    }
}