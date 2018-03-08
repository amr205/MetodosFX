package content.Utilities;


import content.resources.lang.Resources;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import content.methods.TableMethod;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class UseMethod {
    public static void calculateResult(ObservableResourceFactory RESOURCE_FACTORY, TableView table, Label resultLabel, TextField equationField, TextField solveStart, TextField solveEnd, TextField solveError, ComboBox methodBox, ArrayList<Object> list){

        TableMethod method = (TableMethod)methodBox.getValue();
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

            if(initB<=initA&&!solveStart.isDisable()&&!solveEnd.isDisable()){
                isCorrect=false;

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
                error.setHeaderText(RESOURCE_FACTORY.getResources().getString("correctFollowingHeader"));
                error.setContentText(RESOURCE_FACTORY.getResources().getString("startingPointMethodDescription"));
                error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                error.show();

                return;

            }
        }catch (Exception e){isCorrect=false;
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
            error.setHeaderText(RESOURCE_FACTORY.getResources().getString("correctFollowingHeader"));
            error.setContentText(RESOURCE_FACTORY.getResources().getString("incorrectRangeMethodDescription"));
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();
            isCorrect = false;
            return;
        }

        try{
            if(!solveError.getText().isEmpty()||!solveError.getText().equals(""))
                errorP = Float.parseFloat(solveError.getText());
        }catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
            error.setHeaderText(RESOURCE_FACTORY.getResources().getString("correctFollowingHeader"));
            error.setContentText(RESOURCE_FACTORY.getResources().getString("incorrectErrorMethodDescription"));
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();
            isCorrect = false;
            return;
        }
        isCorrect = method.validateOptional(RESOURCE_FACTORY,list);
        if(isCorrect){
            method.initializeColumns(table);
            float result = method.doMethod(table, initA, initB, errorP,equationField.getText(),list);
            resultLabel.setText(String.format("%.6f", result)+"");
        }
    }

}