package content.controller;

import content.Main;
import content.Utilities.DrawView;
import content.Utilities.ObservableResourceFactory;
import content.Utilities.UseMethod;
import content.methods.NewtonRaphson;
import content.resources.lang.Resources;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import content.methods.Biseccion;
import content.methods.FalseRule;
import content.methods.PuntoFijo;
import content.methods.TableMethod;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

import java.util.Locale;
import java.util.ResourceBundle;


public class MainController implements Initializable {

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
    MenuItem spanishMenuItem, englishMenuItem, closeMenuItem, newFileMenuItem, defaultThemeMenuItem, darkThemeMenuItem, lightThemeMenuItem;

    @FXML
    Menu fileMenu, languageMenu, styleMenu;


    private final String RESOURCE_NAME = Resources.class.getTypeName() ;
    private final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();

    Stage stage;

    private ChangeListener<TableMethod> listener = null;
    private int selectedItem = 0;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //create list for optional objects for different methods
        optionalObjects = new ArrayList<Object>();

        initializaMethodBox();
        reset();

        leftPane.maxWidthProperty().set(600);
        leftPane.minWidthProperty().set(10);
        centerContent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                splitPane.setDividerPosition(0,600);
            }
        });
    }

    public void draw(ActionEvent actionEvent) {
        DrawView.drawEquation(RESOURCE_FACTORY,lineChart,equationField,drawStart,drawEnd);
    }

    @FXML
    public void reset(){
        RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME));

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


    }

    public void changeLanguage(ActionEvent actionEvent) {
        if(actionEvent.getSource()==spanishMenuItem){
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.forLanguageTag("es")));


        }
        else if(actionEvent.getSource()==englishMenuItem){
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.ROOT));

        }

        initializaMethodBox();


        if(!lineChart.getTitle().equals(equationField.getText()))
            lineChart.setTitle(RESOURCE_FACTORY.getResources().getString("insertInput"));
        else
            DrawView.drawEquation(RESOURCE_FACTORY,lineChart,equationField,drawStart,drawEnd);



    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void changeStyle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==defaultThemeMenuItem){
            stage.getScene().getStylesheets().clear();
        }
        else if(actionEvent.getSource()==darkThemeMenuItem){
            stage.getScene().getStylesheets().add(Main.class.getResource("resources/css/style.css").toString());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void initializaMethodBox(){
        if(listener!=null)
            methodBox.valueProperty().removeListener(listener);
        methodBox.getItems().clear();
        ObservableList<TableMethod> options =
                FXCollections.observableArrayList(
                        new Biseccion(RESOURCE_FACTORY),
                        new FalseRule(RESOURCE_FACTORY),
                        new PuntoFijo(RESOURCE_FACTORY),
                        new NewtonRaphson(RESOURCE_FACTORY)
                );
        methodBox.setItems(options);
        methodBox.getSelectionModel().select(selectedItem);

        listener = new ChangeListener<TableMethod>() {
            @Override public void changed(ObservableValue value, TableMethod old, TableMethod newO) {
                //Show your scene here
                solveEnd.setDisable(false);
                toSolveLabel.setDisable(false);
                optionalObjects.clear();
                optionalFields.getChildren().clear();
                newO.initializeOptional(optionalFields,optionalObjects);
                selectedItem = methodBox.getItems().indexOf(newO);
                if(newO.getClass()==PuntoFijo.class||newO.getClass()==NewtonRaphson.class){
                    solveEnd.setText("");
                    solveEnd.setDisable(true);
                    toSolveLabel.setDisable(true);
                }
            }
        };
        methodBox.valueProperty().addListener(listener);

    }
}
