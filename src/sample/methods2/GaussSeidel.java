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
    public String toString() {
        return "Gauss Seidel";
    }
}

