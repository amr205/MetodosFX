package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utilities.DrawView;
import sample.Utilities.ObservableResourceFactory;
import sample.methods2.NewtonRaphsonMultivar;


public class NewtonRMInputController {

    @FXML
    TextField error, equationG1, equationG2, derivE1x, derivE2x, derivE1y, derivE2y, drawS, drawE, equation1, equation2, startX, startY;

    @FXML
    LineChart lineChart;

    private ObservableResourceFactory RESOURCE_FACTORY;

    private NewtonRaphsonMultivar newton;

    public void graphic(MouseEvent mouseEvent) {
        DrawView.drawTwoEquation(RESOURCE_FACTORY,lineChart,equationG1,equationG2,drawS,drawE);
    }



    public void solve(MouseEvent mouseEvent) {
        if(validate()){
            float errorf,startXf, startYf;
            String equation1s, equation2s, derivEquation1x, derivEquation1y, derivEquation2x, derivEquation2Y;

            errorf=Float.parseFloat(error.getText());
            startXf=Float.parseFloat(startX.getText());
            startYf=Float.parseFloat(startY.getText());

            equation1s = equation1.getText();
            equation2s = equation2.getText();

            derivEquation1x = derivE1x.getText();
            derivEquation1y = derivE1y.getText();

            derivEquation2x = derivE2x.getText();
            derivEquation2Y = derivE2y.getText();

            newton.initTable();

            newton.solve(errorf,startXf,startYf,equation1s,equation2s,derivEquation1x,derivEquation1y,derivEquation2x,derivEquation2Y);


        }


    }


    private boolean validate(){
        try{
            Float.parseFloat(error.getText());
            Float.parseFloat(startX.getText());
            Float.parseFloat(startY.getText());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error en los datos de entrada");
            alert.setContentText("El error o el punto de inicios no pueden ser tomados como números");
            alert.show();
            return false;
        }

        try {

            new ExpressionBuilder(equation1.getText()).variables("x","y").build();
            new ExpressionBuilder(equation2.getText()).variables("x","y").build();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error en los datos de entrada");
            alert.setContentText("Las ecuaciones no pueden ser tomadas como validas");
            alert.show();
            return false;
        }

        try{
            new ExpressionBuilder(derivE1x.getText()).variables("x","y").build();
            new ExpressionBuilder(derivE1y.getText()).variables("x","y").build();

            new ExpressionBuilder(derivE2x.getText()).variables("x","y").build();
            new ExpressionBuilder(derivE2y.getText()).variables("x","y").build();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error en los datos de entrada");
            alert.setContentText("Las derivadas no pueden ser tomadas como validas");
            alert.show();
            return false;
        }


        return true;
    }

    public void setRESOURCE_FACTORY(ObservableResourceFactory RESOURCE_FACTORY) {
        this.RESOURCE_FACTORY = RESOURCE_FACTORY;
    }

    public void setNewton(NewtonRaphsonMultivar newton) {
        this.newton = newton;
    }
}
