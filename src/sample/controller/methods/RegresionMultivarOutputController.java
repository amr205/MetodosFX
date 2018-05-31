package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.methods.model.RegresionLinealModel1;
import sample.methods.model.RegresionLinealModel2;
import sample.methods.model.RegresionMultivarModel;

//Clase encargada de manejar los datos de salida de regresión multivariable

public class RegresionMultivarOutputController {
    @FXML
    TableView<RegresionMultivarModel> table;

    public TableView<RegresionMultivarModel> getTable() {
        return table;
    }
}
