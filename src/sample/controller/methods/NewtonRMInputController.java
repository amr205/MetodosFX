package sample.controller.methods;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Utilities.DrawView;
import sample.Utilities.ObservableResourceFactory;
import sample.methods2.NewtonRaphsonMultivar;


public class NewtonRMInputController {

    @FXML
    TextField error, equationG1, equationG2, derivE1x, derivE2x, derivE1y, derivE2y, drawS, drawE, equation1, equation2;

    @FXML
    LineChart lineChart;

    private ObservableResourceFactory RESOURCE_FACTORY;

    private NewtonRaphsonMultivar newton;

    public void graphic(MouseEvent mouseEvent) {
        DrawView.drawTwoEquation(RESOURCE_FACTORY,lineChart,equationG1,equationG2,drawS,drawE);
    }



    public void solve(MouseEvent mouseEvent) {
    }


    private boolean validate(){
        boolean valid = true;
        try{

        }catch (Exception e){

        }
        return valid;
    }

    public void setRESOURCE_FACTORY(ObservableResourceFactory RESOURCE_FACTORY) {
        this.RESOURCE_FACTORY = RESOURCE_FACTORY;
    }

    public void setNewton(NewtonRaphsonMultivar newton) {
        this.newton = newton;
    }
}
