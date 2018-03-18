package content.controller.methods;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GaussOtputController implements Initializable {

    @FXML
    FlowPane flowPane;

    private int currentIt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentIt = 1;
    }

    public void clear(){
        currentIt = 1;
        flowPane.getChildren().clear();
    }
    public void addIteration(double[][] A, double[] B){
        HBox wrap = new HBox();
        Label itNum = new Label(currentIt+")");
        itNum.setFont(new Font(20));
        Label opening = new Label("(");
        opening.setFont(new Font(45*A.length/3));
        Label closing = new Label(")");
        closing.setFont(new Font(45*A.length/3));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);

        for(int y=0; y<A.length; y++){
            for(int x=0; x<A.length; x++){
                Label temp = new Label( String.format("%.3f%n", A[y][x]));
                temp.setPrefHeight(20);
                temp.setFont(new Font(14));
                gridPane.add(temp,x,y);
            }
            Separator divider = new Separator();
            divider.setPrefHeight(20);
            divider.setOrientation(Orientation.VERTICAL);
            gridPane.add(divider,A.length,y);

            Label temp = new Label(String.format("%.3f%n", B[y]));
            temp.setPrefHeight(20);
            temp.setFont(new Font(14));
            gridPane.add(temp,A.length+1,y);
        }
        wrap.getChildren().addAll(itNum,opening,gridPane,closing);
        flowPane.getChildren().add(wrap);

        currentIt++;

    }
}
