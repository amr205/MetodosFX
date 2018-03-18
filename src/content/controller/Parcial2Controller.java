package content.controller;

import content.Main;
import content.Utilities.DrawView;
import content.Utilities.ObservableResourceFactory;
import content.controller.methods.GaussInputController;
import content.methods.TableMethod;
import content.methods2.Gauss;
import content.methods2.ParentMethod;
import content.resources.lang.Resources;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Parcial2Controller implements Initializable{


    @FXML
    ComboBox<ParentMethod> methodBox;

    @FXML
    Menu fileMenu,problemMenu,languageMenu, styleMenu;

    @FXML
    AnchorPane leftPane, outputSection,inputSection, centerContent;

    @FXML
    SplitPane splitPane;

    @FXML
    Label selectMethodLabel;

    @FXML
    MenuItem newFileMenuItem, closeMenuItem, problem1MenuItem, problem2MenuItem,englishMenuItem,spanishMenuItem,defaultThemeMenuItem,darkThemeMenuItem,lightThemeMenuItem;

    private final String RESOURCE_NAME = Resources.class.getTypeName() ;
    private final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();
    private final Preferences prefs = Preferences.userNodeForPackage(Main.class);

    private Stage stage;
    private ChangeListener<ParentMethod> listener = null;
    private int selectedItem = 0;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //METHODS
    private Gauss gauss;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME));

        leftPane.maxWidthProperty().set(600);
        leftPane.minWidthProperty().set(10);
        centerContent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                splitPane.setDividerPosition(0,600);
            }
        });

        gauss = new Gauss(RESOURCE_FACTORY);

        reset();
    }
    
    public void close() {
        System.exit(0);

    }

    public void reset() {
        bindText();
        updateLanguage();
        methodBox.getValue().initialize(inputSection, outputSection);
        methodBox.getValue().updateLanguage();
    }

    private void bindText(){
        spanishMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("spanishMenuItem"));
        englishMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("englishMenuItem"));
        closeMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("closeMenuItem"));
        newFileMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("newFileMenuItem"));
        defaultThemeMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("defaultThemeMenuItem"));
        darkThemeMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("darkThemeMenuItem"));
        lightThemeMenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("lightThemeMenuItem"));

        fileMenu.textProperty().bind(RESOURCE_FACTORY.getStringBinding("fileMenu"));
        languageMenu.textProperty().bind(RESOURCE_FACTORY.getStringBinding("languageMenu"));
        styleMenu.textProperty().bind(RESOURCE_FACTORY.getStringBinding("styleMenu"));

        problemMenu.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problemMenu"));
        problem1MenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problem1MenuItem"));
        problem2MenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problem2MenuItem"));

        selectMethodLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("selectMethodLabel"));



    }

    @FXML
    public void changeWindow(ActionEvent actionEvent) {
        if(actionEvent.getSource()==problem1MenuItem) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/Parcial1.fxml"));
                Parent root = (Parent) loader.load();
                Parcial1Controller mainController = (Parcial1Controller) loader.getController();
                mainController.setStage(stage);
                Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
                stage.setScene(scene);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        if(actionEvent.getSource()==spanishMenuItem)
            prefs.put("DEFAULT_LANGUAGE","es");
        else if(actionEvent.getSource()==englishMenuItem)
            prefs.put("DEFAULT_LANGUAGE","en");

        updateLanguage();



    }

    private void updateLanguage() {
        String defaultLang = prefs.get("DEFAULT_LANGUAGE","es");

        if(defaultLang.equals("es"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.forLanguageTag("es")));

        else if(defaultLang.equals("en"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.ROOT));

        initializaMethodBox();
    }

    public void changeStyle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==defaultThemeMenuItem){
            stage.getScene().getStylesheets().clear();
        }
        else if(actionEvent.getSource()==darkThemeMenuItem){
            stage.getScene().getStylesheets().add(Main.class.getResource("resources/css/style.css").toString());
        }
        else if(actionEvent.getSource()==lightThemeMenuItem){
            stage.getScene().getStylesheets().add(Main.class.getResource("resources/css/style2.css").toString());

        }
    }





    public void showInfo(ActionEvent actionEvent) {
    }

    private void initializaMethodBox(){
        if(listener!=null)
            methodBox.valueProperty().removeListener(listener);

        methodBox.getItems().clear();
        ObservableList<ParentMethod> options =
                FXCollections.observableArrayList(
                        gauss
                );

        methodBox.setItems(options);


        listener = (value, old, newO) -> {
            if(selectedItem!=methodBox.getItems().indexOf(newO)){
                newO.initialize(inputSection, outputSection);
                newO.updateLanguage();
                selectedItem = methodBox.getItems().indexOf(newO);
            }
            else{
                newO.updateLanguage();

            }
        };

        methodBox.valueProperty().addListener(listener);

        methodBox.getSelectionModel().select(selectedItem);

    }
}
