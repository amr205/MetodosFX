package content;

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


        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainWindow.fxml"));
        primaryStage.setTitle("MétodosFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
        Scene scene = new Scene(root, 1200, 800);
        //scene.getStylesheets().add(getClass().getResource("resources/css/style.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
