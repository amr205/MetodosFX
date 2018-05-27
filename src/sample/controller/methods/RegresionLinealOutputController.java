package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.methods.model.NewtonRaphsonModel;
import sample.methods.model.RegresionLinealModel1;
import sample.methods.model.RegresionLinealModel2;

public class RegresionLinealOutputController {

    @FXML
    TableView<RegresionLinealModel1> table1;

    @FXML
    TableView<RegresionLinealModel2> table2;


    public TableView<RegresionLinealModel1> getTable1() {
        return table1;
    }

    public TableView<RegresionLinealModel2> getTable2() {
        return table2;
    }
}
