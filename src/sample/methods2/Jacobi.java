package sample.methods2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.GaussInputController;
import sample.controller.methods.GaussOtputController;
import sample.controller.methods.JacobiInputController;
import sample.controller.methods.JacobiOtputController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//se encarga de realizar el método y mostrar la información en pantalla


public class Jacobi extends ParentMethod{
    protected JacobiInputController inputController;
    protected JacobiOtputController outputController;

    public Jacobi(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection){
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/JacobiInput.fxml"));
            AnchorPane inputRoot = (AnchorPane) inputLoader.load();
            JacobiInputController inputController = (JacobiInputController) inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/JacobiOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            JacobiOtputController outputController = (JacobiOtputController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);


            this.inputController = inputController;
            this.outputController = outputController;

            inputController.setJacobi(this);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void solve(double[][] A, double[] B, double error) {
        try {

            addTableModel(B.length);

            String[] equations = generateEquations(A, B);
            double[] x = new double[B.length];
            double[] xnAnt = new double[B.length];
            double[] xn = new double[B.length];
            double[] e = new double[B.length];

            int n = 1;

            for (int i = 0; i < xn.length; i++)
                xn[i] = 0;

            for (int i = 0; i < x.length; i++)
                x[i] = 0;

            for (int i = 0; i < e.length; i++)
                e[i] = 1;

            System.out.println(Arrays.toString(e));


            do {
                xnAnt = xn;
                xn = generateXnValues(equations, x);
                x = xn;

                System.out.println(Arrays.toString(e));


                if (n != 1)
                    e = generateErrors(xn, xnAnt);

                addData(n, xnAnt, xn, e);

                n++;


            } while (!errorValidity(e, error));

            System.out.println(Arrays.toString(xn));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }


    }

    private void addTableModel(int nX) {

        TableView<List<String >> myTable = outputController.getTable();
        myTable.getItems().clear();
        myTable.getColumns().clear();

        TableColumn no = new TableColumn<List<String>, String>("No");
        no.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(0)) ;
            }
        });
        myTable.getColumns().add(no);


        //add columns for x
        for(int i=0; i<nX; i++ ) {
            final int tempi = 1+i;
            TableColumn temp = new TableColumn<List<String>, String>("X"+(i+1));
            temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                    return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(tempi));
                }
            });
            myTable.getColumns().add(temp);

        }

        //add columns for xn
        for(int i=0; i<nX; i++ ) {
            final int tempi = 1+nX+i;
            TableColumn temp = new TableColumn<List<String>, String>("Xn"+(i+1));
            temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                    return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(tempi));
                }
            });
            myTable.getColumns().add(temp);

        }

        //add columns for e
        for(int i=0; i<nX; i++ ) {
            final int tempi = 1+nX+nX+i;
            TableColumn temp = new TableColumn<List<String>, String>("e"+(i+1));
            temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                    return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(tempi));
                }
            });
            myTable.getColumns().add(temp);

        }



    }

    private boolean errorValidity(double e[],double v) {
        boolean flag = true;
        for(int i=0; i<e.length; i++){
            if(e[i]>v)
                flag=false;

            System.out.println(e[i]);
        }
        return flag;
    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Jacobi");
        info.setHeaderText("Descripción");
        info.setContentText("La base del método consiste en construir una sucesión convergente definida iterativamente. El límite de esta sucesión es precisamente la solución del sistema. A efectos prácticos si el algoritmo se detiene después de un número finito de pasos se llega a una aproximación al valor de x de la solución del sistema.");
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }

    private String[] generateEquations(double[][] A, double[] B){
        String[] result = new String[B.length];

        for (int i=0; i<B.length; i++) {
            result[i]="(";
            result[i]+=B[i];
            for(int j=0; j<B.length; j++){
                if(j!=i){
                    result[i]+="+("+(A[i][j]*-1)+")x"+(j+1);
                }
            }
            result[i]+=")/("+A[i][i]+")";

        }

        for (String equation:result
             ) {
            System.out.println(equation);

        }
        return result;
    }

    protected double[] generateXnValues(String[] equations, double[] x) throws Exception{
        System.out.println("aqui estoy");
        double[] xn = new double[equations.length];

        for (int i=0; i<equations.length; i++) {
            ExpressionBuilder expressionBuilder = new ExpressionBuilder(equations[i]);

            for (int j=0; j<equations.length; j++)
                expressionBuilder.variable("x"+(j+1));

            Expression expression = expressionBuilder.build();

            for (int j=0; j<equations.length; j++) {
                expression.setVariable("x" + (j + 1), x[j]);
                System.out.println("valor x"+(j+1)+"= "+x[j]);
            }


            double temp = expression.evaluate();

            System.out.println(temp);

            xn[i]=new BigDecimal(temp).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();


            System.out.println(xn[i]);


        }

        System.out.println("aqui llegue");


        return xn;
    }

    private void addData(int n, double[] x, double[] xn, double[] e) {
        TableView<List<String>> myTable = outputController.getTable();


        List<String> unit = new ArrayList<>();
        unit.add(""+n);
        for (double v:x ) {
            unit.add(""+v);
        }
        for (double v:xn) {
            unit.add(""+v);
        }
        for (double v:e) {
            unit.add(""+v);
        }

        myTable.getItems().add(unit);
    }

    private double[] generateErrors(double[] xn, double[] xnAnt) throws  Exception{
        double[] e = new double[xn.length];

        for (int i=0; i<e.length; i++) {
            double temp = (xn[i] - xnAnt[i]) / xn[i] * 100;
            e[i] = new BigDecimal(Math.abs(temp)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        return e;
    }


    @Override
    public String toString(){
        return "Jacobi";
    }

}

