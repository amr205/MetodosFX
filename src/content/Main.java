package content;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainWindow.fxml"));
        primaryStage.setTitle("Hello World "+result);
        Scene scene = new Scene(root, 1200, 800);
        //scene.getStylesheets().add(getClass().getResource("css/style.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
