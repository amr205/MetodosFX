package sample.methods2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.GaussInputController;
import sample.controller.methods.GaussOtputController;
import sample.controller.methods.NewtonRMInputController;

public class NewtonRaphsonMultivar extends ParentMethod{
    protected NewtonRMInputController inputController;
    protected GaussOtputController outputController;

    public NewtonRaphsonMultivar(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/NewtonRMInput.fxml"));
            AnchorPane inputRoot = (AnchorPane) inputLoader.load();
            NewtonRMInputController inputController = (NewtonRMInputController) inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/GaussOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            GaussOtputController outputController = (GaussOtputController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setRESOURCE_FACTORY(RESOURCE_FACTORY);
            inputController.setNewton(this);

            this.inputController = inputController;
            this.outputController = outputController;


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




    @Override
    public String toString(){
        return "Newton Raphson Multivariable";
    }
}
