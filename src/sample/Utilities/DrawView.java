package sample.Utilities;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class DrawView {
    public static boolean drawEquation(ObservableResourceFactory RESOURCE_FACTORY, LineChart lineChart, TextField equationField, TextField drawStart, TextField drawEnd){

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
            error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
            error.setHeaderText(RESOURCE_FACTORY.getResources().getString("equationErrorHeader"));
            error.setContentText(RESOURCE_FACTORY.getResources().getString("equationErrorDescription"));
            error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            error.show();


            return false;
        }


        try {
            drawS = Float.parseFloat(drawStart.getText());
            drawE = Float.parseFloat(drawEnd.getText());

            if (drawE <= drawS) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
                error.setHeaderText(RESOURCE_FACTORY.getResources().getString("correctFollowingHeader"));
                error.setContentText(RESOURCE_FACTORY.getResources().getString("startingPointDrawDescription"));
                error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                error.show();
                isCorrect = false;
            }
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(RESOURCE_FACTORY.getResources().getString("wrongInputTitle"));
            error.setHeaderText(RESOURCE_FACTORY.getResources().getString("correctFollowingHeader"));
            error.setContentText(RESOURCE_FACTORY.getResources().getString("incorrectRangeDrawDescription"));
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
                series.setName(RESOURCE_FACTORY.getResources().getString("graphicTitle"));
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
        return isCorrect;
    }
}
