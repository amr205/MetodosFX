package content;

import content.controller.Parcial1Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.prefs.Preferences;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        double result = new ExpressionBuilder("x+y").variables("x","y").build().
                setVariable("x",15).setVariable("y",6).evaluate();



        //LOAD STAGE
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Parcial1.fxml"));
        Parent root = (Parent)loader.load();
        Parcial1Controller mainController = (Parcial1Controller)loader.getController();
        mainController.setStage(primaryStage);
        primaryStage.setTitle("MétodosFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
