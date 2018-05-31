package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sample.methods2.RegresionLineal;
import sample.methods2.RegresionMulti;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Clase encargada de manejar los datos de entrada de regresión multivariable

public class RegresionMultivarInputController implements Initializable {
    ArrayList<TextField> yList = new ArrayList<TextField>();
    ArrayList<TextField> x0List = new ArrayList<TextField>();
    ArrayList<TextField> x1List = new ArrayList<TextField>();

    RegresionMulti regresionMulti;


    @FXML
    VBox dataVBox;

    @FXML
    TextField x1Value, x2Value;

    @FXML
    Label equationLabel, rLabel, yLabel;




    public void evaluate() {

        boolean input=true;

        float[] y = new float[yList.size()];
        float[] x0 = new float[x0List.size()];
        float[] x1 = new float[x1List.size()];
        float X1=0,X2=0;

        try {
            for (int i=0; i<yList.size(); i++){
                y[i]=Float.parseFloat(yList.get(i).getText());
            }
            for (int i=0; i<x0List.size(); i++){
                x0[i]=Float.parseFloat(x0List.get(i).getText());

            }
            for (int i=0; i<x1List.size(); i++){
                x1[i]=Float.parseFloat(x1List.get(i).getText());

            }
            X1 = Float.parseFloat(x1Value.getText());
            X2 = Float.parseFloat(x1Value.getText());

        }catch (Exception e){
            input=false;
        }

        if (input){
            regresionMulti.regresion(x0,x1,y,y.length,X1,X2);

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos no validos");
            alert.setContentText("Verifique la entrada de los datos");
            alert.show();
        }


    }

    public void addRow() {
        GridPane gridPane = new GridPane();
        Label x0Label = new Label("x1= ");
        Label x1Label = new Label("x2= ");
        Label yLabel = new Label("y= ");
        TextField x0TextField = new TextField();
        TextField x1TextField = new TextField();
        TextField yTextField = new TextField();


        x0List.add(x0TextField);
        x1List.add(x1TextField);
        yList.add(yTextField);

        gridPane.add(yLabel,0,0);
        gridPane.add(yTextField,1,0);
        gridPane.add(x0Label,2,0);
        gridPane.add(x0TextField,3,0);
        gridPane.add(x1Label,0,1);
        gridPane.add(x1TextField,1,1);


        dataVBox.getChildren().addAll(gridPane, new Separator());


    }

    public void removeRow(MouseEvent mouseEvent) {
        if(dataVBox.getChildren().size()>=1)
            dataVBox.getChildren().remove(dataVBox.getChildren().size()-1);

        if(x1List.size()>=1)
            x1List.remove(x1List.size()-1);
        if(x0List.size()>=1)
            x0List.remove(x0List.size()-1);
        if(yList.size()>=1)
            yList.remove(yList.size()-1);
    }

    public void setRegresionMulti(RegresionMulti regresionMulti) {
        this.regresionMulti = regresionMulti;
    }

    public void setR(String r){
        rLabel.setText(r);
    }

    public void setEquation(String r){
        equationLabel.setText(r);
    }

    public void setY(String r){
        yLabel.setText(r);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRow();
    }
}
