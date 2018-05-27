package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegresionPoliOutputController {
    @FXML
    TableView<List<String>> table;




    public TableView<List<String>> getTable() {
        return table;
    }
}
