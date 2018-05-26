package sample;

import sample.Utilities.ObservableResourceFactory;
import sample.controller.Parcial1Controller;
import sample.controller.Parcial2Controller;
import sample.controller.Parcial3Controller;
import sample.resources.lang.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Main extends Application {

    public static final String RESOURCE_NAME = Resources.class.getTypeName() ;
    public static final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();
    private final  Preferences prefs = Preferences.userNodeForPackage(Main.class);


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Asigna los resources a la resource factory encargada del idioma
        RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME));

        //carga el último tipo de método abierto
        //por ahora solución de ecuación o sistema de ecuaciones
        loadLastOpened(primaryStage);




    }

    private void loadLastOpened(Stage primaryStage) throws Exception{
        int window =  prefs.getInt("LAST_WINDOW", 1);

        if(window==1)
            loadWindow1(primaryStage);
        else if(window==2)
            loadWindow2(primaryStage);
        else if(window==3)
            loadWindow3(primaryStage);
        else
            loadWindow1(primaryStage);
    }

    private void loadWindow3(Stage primaryStage) throws Exception {
        FXMLLoader loader;
        loader =  new FXMLLoader(Main.class.getResource("fxml/Parcial3.fxml"));
        Parent root = (Parent)loader.load();
        Parcial3Controller mainController = (Parcial3Controller) loader.getController();
        primaryStage.setTitle("MétodosFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        mainController.setStage(primaryStage);
    }

    private void loadWindow2(Stage primaryStage) throws Exception{
        FXMLLoader loader;
        loader =  new FXMLLoader(Main.class.getResource("fxml/Parcial2.fxml"));
        Parent root = (Parent)loader.load();
        Parcial2Controller mainController = (Parcial2Controller) loader.getController();
        primaryStage.setTitle("MétodosFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        mainController.setStage(primaryStage);
    }

    private void loadWindow1(Stage primaryStage) throws Exception {
        FXMLLoader loader;
        loader =  new FXMLLoader(Main.class.getResource("fxml/Parcial1.fxml"));
        Parent root = (Parent)loader.load();
        Parcial1Controller mainController = (Parcial1Controller)loader.getController();
        primaryStage.setTitle("MétodosFX");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/icon.png")));
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        mainController.setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
