package content;

import content.controller.MainController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        double result = new ExpressionBuilder("x+y").variables("x","y").build().
                setVariable("x",15).setVariable("y",6).evaluate();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainWindow.fxml"));
        Parent root = (Parent)loader.load();
        MainController mainController = (MainController)loader.getController();
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
