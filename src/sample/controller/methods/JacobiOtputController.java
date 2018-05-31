package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//Clase encargada de manejar los datos de salida de jacobi

public class JacobiOtputController implements Initializable{
    @FXML
    TableView<List<String>> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public TableView<List<String>> getTable() {
        return table;
    }
}
