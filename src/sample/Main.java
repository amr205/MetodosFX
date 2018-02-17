package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        double result = new ExpressionBuilder("x+y").variables("x","y").build().
                setVariable("x",15).setVariable("y",6).evaluate();


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World "+result);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
