package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Methods.Biseccion;
import sample.Methods.FalseRule;
import sample.Methods.tableMethod;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    SplitPane splitPane;

    @FXML
    AnchorPane leftPane;

    @FXML
    TextField equationField, drawStart, drawEnd, solveStart, solveEnd,errorField;

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
        String content =
                "BUILT IN OPERATORS: \n" +
                        "    Addition: 2 + 2\n" +
                        "    Subtraction: 2 - 2\n" +
                        "    Multiplication: 2 * 2\n" +
                        "    Division: 2 / 2\n" +
                        "    Exponentation: 2 ^ 2\n" +
                        "    Unary Minus,Plus (Sign Operators): +2 - (-2)\n" +
                        "    Modulo: 2 % 2\n\n"+
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
                "    signum: signum function";

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("How to enter a equation");
        info.setHeaderText("Use the follow operators and functions: \n" +
                "Example: log(x)*sin(x)+(1/3)x");
        info.setContentText(content);
        info.show();
    }

    @FXML
    protected void calculateResult(){
        DrawView.drawEquation(lineChart,equationField,drawStart,drawEnd);
        UseMethod.calculateResult(methodTable,resultLabel,equationField,solveStart,solveEnd,errorField,methodBox);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lineChart.setTitle("Please insert input");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Graphic");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 0));

        series.getData().add(new XYChart.Data(12, 5));

        lineChart.getData().add(series);

        ObservableList<tableMethod> options =
                FXCollections.observableArrayList(
                        new Biseccion(),
                        new FalseRule()
                );
        methodBox.setItems(options);

        methodBox.getSelectionModel().selectFirst();

        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));
        leftPane.minWidthProperty().bind(splitPane.widthProperty().multiply(0.01));
    }

    public void draw(ActionEvent actionEvent) {
        DrawView.drawEquation(lineChart,equationField,drawStart,drawEnd);
    }
}
