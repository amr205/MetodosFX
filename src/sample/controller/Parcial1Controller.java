package sample.controller;

import sample.Main;
import sample.Utilities.DrawView;
import sample.Utilities.ObservableResourceFactory;
import sample.Utilities.UseMethod;
import sample.methods.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class Parcial1Controller implements Initializable {

    ArrayList<Object> optionalObjects;

    @FXML
    AnchorPane centerContent;

    @FXML
    VBox optionalFields;

    @FXML
    GridPane gridPane;

    @FXML
    SplitPane splitPane;

    @FXML
    AnchorPane leftPane;

    @FXML
    TextField equationField, drawStart, drawEnd, solveStart, solveEnd,errorField;

    @FXML
    ComboBox<TableMethod> methodBox;

    @FXML
    Label resultLabel, resultLabelText,toSolveLabel, toDrawLabel, solveFromLabel, drawFromLabel, methodLabel, equationLabel, inputLabel;

    @FXML
    LineChart lineChart;

    @FXML
    TableView methodTable;

    @FXML
    Button calculateBtn, howBtn, drawBtn;

    @FXML
    MenuItem spanishMenuItem, englishMenuItem, closeMenuItem, newFileMenuItem, defaultThemeMenuItem, darkThemeMenuItem, lightThemeMenuItem, problem1MenuItem, problem2MenuItem, problem3MenuItem;

    @FXML
    Menu fileMenu, languageMenu, styleMenu, problemMenu;


    private String RESOURCE_NAME;
    private ObservableResourceFactory RESOURCE_FACTORY;

    private final  Preferences prefs = Preferences.userNodeForPackage(Main.class);

    private Stage stage;

    private ChangeListener<TableMethod> listener = null;
    private int selectedItem = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //init resource factory
        //Obtener la resource factory mediante la cual podemos obtener el texto de acuerdo al idioma
        RESOURCE_FACTORY = Main.RESOURCE_FACTORY;
        RESOURCE_NAME = Main.RESOURCE_NAME;

        //create list for optional objects for different methods
        //Crear un ArrayList sobre el cual se guardarán objetos opcionales
        optionalObjects = new ArrayList<Object>();

        //Propiedades para mantener el aspecto en la aplicación
        leftPane.maxWidthProperty().set(600);
        leftPane.minWidthProperty().set(10);
        centerContent.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> splitPane.setDividerPosition(0,600));

        initializaMethodBox();
        reset();

        prefs.putInt("LAST_WINDOW",1);

    }

    @FXML
    public void reset(){

        lineChart.setTitle(RESOURCE_FACTORY.getResources().getString("insertInput"));
        lineChart.getData().clear();
        methodTable.getColumns().clear();

        methodBox.getSelectionModel().selectFirst();

        equationField.setText("");
        drawStart.setText("");
        drawEnd.setText("");
        solveStart.setText("");
        solveEnd.setText("");
        errorField.setText("");

        solveEnd.setDisable(false);
        toSolveLabel.setDisable(false);

        resultLabel.setText("");

        bindText();
        updateLanguage();
    }

    private void bindText(){
        resultLabelText.textProperty().bind(RESOURCE_FACTORY.getStringBinding("resultLabelText"));
        toSolveLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("toSolveLabel"));
        toDrawLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("toDrawLabel"));
        solveFromLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("solveFromLabel"));
        drawFromLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("drawFromLabel"));
        methodLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("methodLabel"));
        equationLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("equationLabel"));
        inputLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("inputLabel"));

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

        calculateBtn.textProperty().bind(RESOURCE_FACTORY.getStringBinding("calculateBtn"));
        drawBtn.textProperty().bind(RESOURCE_FACTORY.getStringBinding("drawBtn"));

        problemMenu.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problemMenu"));
        problem1MenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problem1MenuItem"));
        problem2MenuItem.textProperty().bind(RESOURCE_FACTORY.getStringBinding("problem2MenuItem"));

    }

    private void initializaMethodBox(){
        //se hacen distintas cosas para además de inicializar el comboBox se actualizen los nombres según el idioma
        if(listener!=null)
            methodBox.valueProperty().removeListener(listener);
        methodBox.getItems().clear();
        ObservableList<TableMethod> options =
                FXCollections.observableArrayList(
                        new Biseccion(RESOURCE_FACTORY),
                        new FalseRule(RESOURCE_FACTORY),
                        new PuntoFijo(RESOURCE_FACTORY),
                        new NewtonRaphson(RESOURCE_FACTORY),
                        new Secante(RESOURCE_FACTORY)
                );
        methodBox.setItems(options);
        methodBox.getSelectionModel().select(selectedItem);

        listener = (value, old, newO) -> {

            solveEnd.setDisable(false);
            toSolveLabel.setDisable(false);
            optionalObjects.clear();
            optionalFields.getChildren().clear();
            newO.initializeOptional(optionalFields,optionalObjects);


            selectedItem = methodBox.getItems().indexOf(newO);
            if(newO.getClass()==PuntoFijo.class||newO.getClass()==NewtonRaphson.class){
                solveEnd.setDisable(true);
                toSolveLabel.setDisable(true);
            }
        };
        methodBox.valueProperty().addListener(listener);

    }

    @FXML
    protected void showInfo(){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("howToTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("howToHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("howToDescription"));
        info.show();
    }

    @FXML
    protected void calculateResult(){
        if(DrawView.drawEquation(RESOURCE_FACTORY,lineChart,equationField,drawStart,drawEnd))
            UseMethod.calculateResult(RESOURCE_FACTORY,methodTable,resultLabel,equationField,solveStart,solveEnd,errorField,methodBox,optionalObjects);
    }

    @FXML
    public void draw(ActionEvent actionEvent) {
        DrawView.drawEquation(RESOURCE_FACTORY,lineChart,equationField,drawStart,drawEnd);
    }

    public void changeLanguage(ActionEvent actionEvent) {
        if(actionEvent.getSource()==spanishMenuItem)
            prefs.put("DEFAULT_LANGUAGE","es");
        else if(actionEvent.getSource()==englishMenuItem)
            prefs.put("DEFAULT_LANGUAGE","en");

        updateLanguage();
    }

    @FXML
    private void updateLanguage(){
        String defaultLang = prefs.get("DEFAULT_LANGUAGE","es");

        if(defaultLang.equals("es"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.forLanguageTag("es")));

        else if(defaultLang.equals("en"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.ROOT));


        initializaMethodBox();


        if(!lineChart.getTitle().equals(equationField.getText()))
            lineChart.setTitle(RESOURCE_FACTORY.getResources().getString("insertInput"));
        else
            DrawView.drawEquation(RESOURCE_FACTORY,lineChart,equationField,drawStart,drawEnd);

    }

    @FXML
    public void changeStyle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==defaultThemeMenuItem)
            prefs.put("DEFAULT_STYLE","default");

        else if(actionEvent.getSource()==darkThemeMenuItem)
            prefs.put("DEFAULT_STYLE","dark");

        else if(actionEvent.getSource()==lightThemeMenuItem)
            prefs.put("DEFAULT_STYLE","light");

        updateStyle();

    }

    private void updateStyle(){
        try {
            String defaultStyle = prefs.get("DEFAULT_STYLE", "default");


            if (defaultStyle.equals("default"))
                stage.getScene().getStylesheets().clear();

            else if (defaultStyle.equals("dark"))
                stage.getScene().getStylesheets().add(Main.class.getResource("resources/css/style.css").toString());

            else if (defaultStyle.equals("light"))
                stage.getScene().getStylesheets().add(Main.class.getResource("resources/css/style2.css").toString());


        }catch (Exception e){
            System.out.println(e.getMessage()+e.getCause()+e.toString());
        }

    }

    @FXML
    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }



    public void setStage(Stage stage) {
        this.stage = stage;
        updateStyle();

    }



    @FXML
    public void showDescription(ActionEvent actionEvent) {
        methodBox.getValue().showDescription();
    }

    @FXML
    public void changeWindow(ActionEvent actionEvent) {
        if(actionEvent.getSource()==problem1MenuItem){

        }
        if(actionEvent.getSource()==problem2MenuItem){
            try{
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/Parcial2.fxml"));
                Parent root = (Parent)loader.load();
                Parcial2Controller mainController = (Parcial2Controller)loader.getController();
                Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                mainController.setStage(stage);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if(actionEvent.getSource()==problem3MenuItem) {
            try {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/Parcial3.fxml"));
                Parent root = (Parent) loader.load();
                Parcial3Controller mainController = (Parcial3Controller) loader.getController();
                Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
                stage.setScene(scene);
                mainController.setStage(stage);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
