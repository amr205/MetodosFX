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
        //Este método se encarga de dibujar la ecuación haciendo uso de un lineChart de javaFx
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

    public static boolean drawTwoEquation(ObservableResourceFactory RESOURCE_FACTORY, LineChart lineChart, TextField equationField,TextField equationField2, TextField drawStart, TextField drawEnd){
        //Este método se encarga de dibujar la ecuación haciendo uso de un lineChart de javaFx
        Expression expression, expression2;
        float drawS, drawE;
        drawS =0;
        drawE = 1;
        expression = new ExpressionBuilder("x").variable("x").build();
        expression2 = new ExpressionBuilder("x").variable("x").build();

        Boolean isCorrect = true;
        try{
            expression = new ExpressionBuilder(equationField.getText()).variable("x").build();
            expression2 = new ExpressionBuilder(equationField2.getText()).variable("x").build();
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
                lineChart.setTitle(equationField.getText()+"   |  "+equationField2.getText());
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("E1");
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

                XYChart.Series series2 = new XYChart.Series();
                series2.setName("E2");
                //populating the series with data
                for (float i = drawS; i <= drawE; i += inc) {
                    try {
                        float y = (float) expression2.setVariable("x", i).evaluate();
                        if (!Float.isNaN(y)&&!Float.isInfinite(y)) {
                            series2.getData().add(new XYChart.Data(i, y));

                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                lineChart.getData().add(series2);
            }
        } catch (Exception e) {
            //error
        }
        return isCorrect;
    }
}
