package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.methods2.RegresionLineal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//Clase encargada de manejar los datos de entrada de regresión

public class RegresionLinealInputController implements Initializable {
    ArrayList<TextField> xList = new ArrayList<TextField>();
    ArrayList<TextField> yList = new ArrayList<TextField>();

    RegresionLineal regresionLineal;


    @FXML
    VBox dataVBox;

    @FXML
    TextField xValue;

    @FXML
    Label equationLabel, rLabel, yLabel;




    public void evaluate() {

        boolean input=true;
        float[] x = new float[xList.size()];
        float[] y = new float[yList.size()];
        float X=0;

        try {


            for (int i=0; i<xList.size(); i++){
                x[i]=Float.parseFloat(xList.get(i).getText());

            }
            for (int i=0; i<yList.size(); i++){
                y[i]=Float.parseFloat(yList.get(i).getText());
            }

            X = Float.parseFloat(xValue.getText());

        }catch (Exception e){
            input=false;
        }

        if (input){
            regresionLineal.solve(x,y,x.length,X);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos no validos");
            alert.setContentText("Verifique la entrada de los datos");
            alert.show();
        }


    }

    public void addRow() {
        HBox hBox = new HBox(15);
        Label xLabel = new Label("x"+dataVBox.getChildren().size()+"= ");
        Label yLabel = new Label("y"+dataVBox.getChildren().size()+"= ");
        TextField xTextField = new TextField();
        TextField yTextField = new TextField();

        xList.add(xTextField);
        yList.add(yTextField);

        hBox.getChildren().addAll(xLabel,xTextField,yLabel,yTextField);

        dataVBox.getChildren().add(hBox);


    }

    public void removeRow(MouseEvent mouseEvent) {
        if(dataVBox.getChildren().size()>=1)
            dataVBox.getChildren().remove(dataVBox.getChildren().size()-1);

        if(xList.size()>=1)
           xList.remove(xList.size()-1);
        if(yList.size()>=1)
            yList.remove(yList.size()-1);
    }

    public void setRegresionLineal(RegresionLineal regresionLineal) {
        this.regresionLineal = regresionLineal;
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
