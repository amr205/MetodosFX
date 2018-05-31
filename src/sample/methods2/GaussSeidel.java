package sample.methods2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
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
import sample.controller.methods.JacobiInputController;
import sample.controller.methods.JacobiOtputController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//se encarga de realizar el método y mostrar la información en pantalla


public class GaussSeidel extends Jacobi {


    public GaussSeidel(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    protected double[] generateXnValues(String[] equations, double[] x) throws Exception {
        System.out.println("aqui estoy");
        double[] xn = new double[equations.length];

        for (int i = 0; i < equations.length; i++) {
            int sub = 0;
            ExpressionBuilder expressionBuilder = new ExpressionBuilder(equations[i]);

            for (int j = 0; j < equations.length; j++)
                expressionBuilder.variable("x" + (j + 1));

            Expression expression = expressionBuilder.build();

            for (int j = 0; j < i; j++) {
                expression.setVariable("x" + (j + 1), xn[j]);
                System.out.println("valor x" + (j + 1) + "= " + xn[j]);
            }

            for (int j = i; j < equations.length; j++) {
                expression.setVariable("x" + (j + 1), x[j]);
                System.out.println("valor x" + (j + 1) + "= " + x[j]);
            }


            double temp = expression.evaluate();

            System.out.println(temp + "SACO VALOR");

            xn[i] = new BigDecimal(temp).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();


            System.out.println(xn[i]);

            System.out.println(temp + "Termino vuelta");
        }

        System.out.println("aqui llegue");


        return xn;
    }

    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Gauss Seidel");
        info.setHeaderText("Descripción");
        info.setContentText("Similar al método de Jacobi solo que en este se toma en cuanta el resultado inmediatamente que se calculo para las demás iteraciones\n" +
                "Aunque este método puede aplicarse a cualquier sistema de ecuaciones lineales que produzca una matriz (cuadrada, naturalmente pues para que exista solución única, el sistema debe tener tantas ecuaciones como incógnitas) de coeficientes con los elementos de su diagonal no-nulos, la convergencia del método solo se garantiza si la matriz es diagonalmente dominante o si es simétrica y, a la vez, definida positiva.");
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }

    @Override
    public String toString() {
        return "Gauss Seidel";
    }
}

