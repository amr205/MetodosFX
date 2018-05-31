package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.methods2.InterpolacionN;
import sample.methods2.RegresionPoli;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//Clase encargada de manejar los datos de entrada para el método de interpolación

public class InterpolacionNInputController implements Initializable {
    ArrayList<TextField> xList = new ArrayList<TextField>();
    ArrayList<TextField> yList = new ArrayList<TextField>();

    InterpolacionN interpolacionN;


    @FXML
    VBox dataVBox, resultBox;

    @FXML
    TextField xValue;

    @FXML
    Label yLabel;




    public void evaluate() {

        boolean input=true;
        double[] x = new double[xList.size()];
        double[] y = new double[yList.size()];
        int grado=0;

        double X=0.0;

        try {


            for (int i=0; i<xList.size(); i++){
                x[i]=Float.parseFloat(xList.get(i).getText());

            }
            for (int i=0; i<yList.size(); i++){
                y[i]=Float.parseFloat(yList.get(i).getText());
            }



            X = Double.parseDouble(xValue.getText());

        }catch (Exception e){
            input=false;
        }

        if (input){
            interpolacionN.solve(x,y,X);
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
        Label yLabel = new Label("f"+dataVBox.getChildren().size()+"(x)= ");
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

    public void setInterpolacionN(InterpolacionN interpolacionN) {
        this.interpolacionN = interpolacionN;
    }

    public void setY(String r){
        yLabel.setText(r);
    }

    public VBox getResultBox() {
        return resultBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRow();
    }
}

