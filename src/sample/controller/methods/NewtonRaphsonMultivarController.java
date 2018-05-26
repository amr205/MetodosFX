package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.methods.model.NewtonRaphsonModel;
import sample.methods.model.NewtonRapsonMultivarModel;
import sample.methods2.NewtonRaphsonMultivar;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewtonRaphsonMultivarController {
    @FXML
    TableView<NewtonRapsonMultivarModel> table;


    public TableView<NewtonRapsonMultivarModel> getTable() {
        return table;
    }
}
