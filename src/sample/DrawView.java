package sample;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.awt.*;

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
            System.out.println("incorrect input in equation");
            isCorrect = false;
        }
        try{
            drawS = Float.parseFloat(drawStart.getText());
            drawE = Float.parseFloat(drawEnd.getText());

            if(drawE<=drawS){
                System.out.println("drawE needs to be higher than drawS");
                isCorrect=false;
            }
        }catch (Exception e){
            System.out.println("incorrect input in drawing range");
            isCorrect = false;
        }

        try{
            if(isCorrect){
                lineChart.getData().remove(0);
                lineChart.setTitle(equationField.getText());
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("Graphic");
                //populating the series with data
                for(float i= drawS ; i<= drawE ;i+=0.05){
                    try {
                        float y = (float)expression.setVariable("x", i).evaluate();
                        if(!Float.isNaN(y)) {
                            series.getData().add(new XYChart.Data(i, y));

                        }

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                lineChart.getData().add(series);
            }
        }catch (Exception e){

        }
    }
}
