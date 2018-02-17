package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    TextField equationField, drawStart, drawEnd, solveStart, solveEnd;

    @FXML
    ComboBox methodBox;

    @FXML
    Label resultLabel;

    @FXML
    LineChart lineChart;

    @FXML
    TableView methodTable;

    @FXML
    Button calculateBtn, howBtn;

    @FXML
    protected void showInfo(){
        System.out.println("\n" +
                "BUILT IN OPERATORS: \n" +
                        "    Addition: 2 + 2\n" +
                        "    Subtraction: 2 - 2\n" +
                        "    Multiplication: 2 * 2\n" +
                        "    Division: 2 / 2\n" +
                        "    Exponentation: 2 ^ 2\n" +
                        "    Unary Minus,Plus (Sign Operators): +2 - (-2)\n" +
                        "    Modulo: 2 % 2\n"+
                "BUILT IN FUNCTIONS: \n" +
                "    abs: absolute value\n" +
                "    acos: arc cosine\n" +
                "    asin: arc sine\n" +
                "    atan: arc tangent\n" +
                "    cbrt: cubic root\n" +
                "    ceil: nearest upper integer\n" +
                "    cos: cosine\n" +
                "    cosh: hyperbolic cosine\n" +
                "    exp: euler's number raised to the power (e^x)\n" +
                "    floor: nearest lower integer\n" +
                "    log: logarithmus naturalis (base e)\n" +
                "    log10: logarithm (base 10)\n" +
                "    log2: logarithm (base 2)\n" +
                "    sin: sine\n" +
                "    sinh: hyperbolic sine\n" +
                "    sqrt: square root\n" +
                "    tan: tangent\n" +
                "    tanh: hyperbolic tangent\n" +
                "    signum: signum function");
    }

    @FXML
    protected void calculateResult(){
        Expression expression;
        float drawS, drawE;
        drawS =0;
        drawE = 1;
        expression = new ExpressionBuilder("x").variable("x").build();

        Boolean isCorrect = true;
        try{
            expression = new ExpressionBuilder(equationField.getText()).variable("x").build();
        }catch (Exception e){
            System.out.println("incorrect input in equation");
            isCorrect = false;
        }
        try{
            drawS = Float.parseFloat(drawStart.getText());
            drawE = Float.parseFloat(drawEnd.getText());

            if(drawE<=drawS){
                System.out.println("drawE needs to be higher than drawS");
                isCorrect=false;
            }
        }catch (Exception e){
            System.out.println("incorrect input in drawing range");
            isCorrect = false;
        }

        try{
            if(isCorrect){
                lineChart.getData().remove(0);
                lineChart.setTitle(equationField.getText());
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("Graphic");
                //populating the series with data
                for(float i= drawS ; i<= drawE ;i+=0.05){
                    try {
                        float y = (float)expression.setVariable("x", i).evaluate();
                        if(!Float.isNaN(y)) {
                            series.getData().add(new XYChart.Data(i, y));

                        }

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                lineChart.getData().add(series);
            }
        }catch (Exception e){

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(equationField.getText()+":)");


        lineChart.setTitle("Test charting");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My Test Data");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);






    }
}
