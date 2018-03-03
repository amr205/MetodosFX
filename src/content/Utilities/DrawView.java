package content.Utilities;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DrawView {

    public static void drawEquation(LineChart lineChart, TextField equationField, TextField drawStart, TextField drawEnd){
        Expression expression;
        float drawS, drawE;
        drawS =0;
        drawE = 1;
        expression = new ExpressionBuilder("x").variable("x").build();

        Boolean isCorrect = true;
        try{
            expression = new ExpressionBuilder(equationField.getText()).variable("x").build();
        }catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Wrong Input");
            error.setHeaderText("The equation is not valid");
            error.setContentText("Check you are using x as variable (not X)\nMore info in: How to enter equation");
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();


            return;
        }


        try {
            drawS = Float.parseFloat(drawStart.getText());
            drawE = Float.parseFloat(drawEnd.getText());

            if (drawE <= drawS) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error Wrong Input");
                error.setHeaderText("Please correct the following");
                error.setContentText("Starting point of the graphic needs to be lower than final point");
                error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                error.show();
                isCorrect = false;
            }
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error Wrong Input");
            error.setHeaderText("Please correct the following");
            error.setContentText("Incorrect input in range of the graphic, check you are entering only numbers");
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();
            isCorrect = false;
        }

        try {
            if (isCorrect) {
                float inc = 0.1f;
                if(drawE-drawS>100)
                    inc = (drawE-drawS)/500;
                if(drawE-drawS<10)
                    inc = 0.01f;
                if(drawE-drawS<.5f)
                    inc = 0.001f;
                if(drawE-drawS<.05f)
                    inc = (drawE-drawS)/500;



                lineChart.getData().clear();
                lineChart.setTitle(equationField.getText());
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("Graphic");
                //populating the series with data
                for (float i = drawS; i <= drawE; i += inc) {
                    try {
                        float y = (float) expression.setVariable("x", i).evaluate();
                        if (!Float.isNaN(y)&&!Float.isInfinite(y)) {
                            series.getData().add(new XYChart.Data(i, y));

                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                lineChart.getData().add(series);
            }
        } catch (Exception e) {
            //error
        }

    }
}
