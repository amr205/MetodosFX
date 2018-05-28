package sample.methods2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.LagrangeInputController;
import sample.controller.methods.RegresionPoliInputController;
import sample.controller.methods.RegresionPoliOutputController;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Lagrange extends ParentMethod{
    protected LagrangeInputController inputController;


    float matriz[][];
    DecimalFormat deci = new DecimalFormat("#.######");

    String s;
    double x;


    public Lagrange(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/LagrangeInput.fxml"));
            Parent inputRoot =  inputLoader.load();
            LagrangeInputController inputController =  inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/GaussOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            //RegresionPoliOutputController outputController = outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);


            inputController.setLagrange(this);

            this.inputController = inputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descGaussHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descGaussDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }



    public void evaluar(int n,float[][]matriz,double X){
        this.matriz=matriz;
        this.x=X;



        double f,mul=0;
        for (int i = 0; i <= n; i++) {
            f=matriz[i][1];
            mul+=(multi(n,i)*f);
        }
        inputController.setY(""+deci.format(mul));
        //JOptionPane.showMessageDialog(null, deci.format(mul));
    }

    private double multi(int n, int i){
        double mul=1;
        for (int j = 0; j <= n; j++) {
            if(j!=i){
                mul*=((x-matriz[j][0])/(matriz[i][0]-matriz[j][0]));
            }
        }
        return mul;
    }



    @Override
    public String toString(){
        return "Interpolación de Lagrange";
    }
}

