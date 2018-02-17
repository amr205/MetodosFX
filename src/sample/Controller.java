package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;


public class Controller {
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
    Button calculateBtn;

    @FXML
    protected void calculateResult(){
        System.out.println(equationField.getText());
    }


}
