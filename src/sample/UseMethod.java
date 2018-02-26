package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import sample.Methods.MethodModel.Person;
import sample.Methods.tableMethod;

import javax.swing.text.TabableView;

public class UseMethod {
    public static void calculateResult(TableView table, Label resultLabel, TextField equationField, TextField solveStart, TextField solveEnd, TextField solveError, ComboBox methodBox){
        tableMethod method = (tableMethod)methodBox.getValue();
        boolean isCorrect = true;
        float initA, initB, errorP;
        initA = 0;
        initB=0;
        errorP = 0.01f;
        try{
            if(!solveStart.isDisable())
                initA = Float.parseFloat(solveStart.getText());
            if(!solveEnd.isDisable())
                initB = Float.parseFloat(solveEnd.getText());


            if(!solveError.getText().isEmpty()||!solveError.getText().equals(""))
                errorP = Float.parseFloat(solveError.getText());

            if(initB<=initA){
                isCorrect=false;

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error Wrong Input");
                error.setHeaderText("Please correct the following");
                error.setContentText("Starting point of the method needs to be lower than final point");
                error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                error.show();

            }
        }catch (Exception e){isCorrect=false;
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Wrong Input");
            error.setHeaderText("Please correct the following");
            error.setContentText("Incorrect input in range of the method, check you are entering only numbers");
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();
            isCorrect = false;}

        if(isCorrect){
            method.initializeColumns(table);
            float result = method.doMethod(table, initA, initB, errorP,equationField.getText());
            resultLabel.setText(String.format("%.6f", result)+"");
        }
    }

}