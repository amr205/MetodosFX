package sample.methods2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.InterpolacionNInputController;
import sample.controller.methods.RegresionPoliInputController;
import sample.controller.methods.RegresionPoliOutputController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InterpolacionN extends ParentMethod{
    protected InterpolacionNInputController inputController;


    private double[] b;
    private double[][] Anterior;    // Valor previamente calculado
    private double[] x;
    private double[] fx;
    private boolean[][] Comprobar;   // ¿El valor ya fue calculado?

    public InterpolacionN(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/InterpolacionNInput.fxml"));
            Parent inputRoot =  inputLoader.load();
            InterpolacionNInputController inputController =  inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/GaussOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setInterpolacionN(this);


            this.inputController = inputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descGaussHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descGaussDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }

    @Override
    public String toString(){
        return "Interpolación orden N";
    }

    public void solve(double[] x, double[] fx, double xValue) {
        double[] b = solucionar(x, fx);

        VBox vBox = inputController.getResultBox();

        for (int i = 0; i < b.length; i++) {
            Label label = new Label("b"+i+"= "+b[i]);

            vBox.getChildren().add(label);

            System.out.println(b[i]);
        }
        System.out.println("f(" + 2.45 + ") = " + Evaluar(2, b, x));

        inputController.setY(""+Evaluar(xValue,b,x));
    }

    public  double[] solucionar(double[] x, double[] fx) {
        int n = x.length;
        b = new double[n];
        Comprobar = new boolean[n][n];
        Anterior = new double[n][n];
        this.x = x;
        this.fx = fx;
        b[0] = fx[0];
        diferenciasDivididas(x.length-1, 0);
        return b;
    }

    public  double diferenciasDivididas(int i, int k) {
        if (i == k) return fx[i];
        double f1 = 0;
        if (Comprobar[i-1][k]) {
            f1 = Anterior[i-1][k];
        } else {
            f1 = diferenciasDivididas(i-1, k);
        }
        double f2 = 0;
        if (Comprobar[i][k+1]) {
            f2 = Anterior[i][k+1];
        } else {
            f2 = diferenciasDivididas(i, k+1);
        }
        double dd = (f1 - f2) / (x[k] - x[i]);
        Comprobar[i][k] = true;
        Anterior[i][k] = dd;
        if (k == 0)     // b[i] = f[i,0]
            b[i] = dd;
        return dd;
    }

    public  double Evaluar(double x, double[] b, double[] xs) {
        double res = 0;
        for (int i = 0; i < b.length; i++) {
            double acum = b[i];
            for (int j = 0; j < i; j++)
                acum *=  (x - xs[j]);
            res += acum;
        }
        return res;
    }

}

