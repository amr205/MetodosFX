package content.controller;

import content.Utilities.DrawView;
import content.Utilities.ObservableResourceFactory;
import content.Utilities.UseMethod;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import content.methods.Biseccion;
import content.methods.FalseRule;
import content.methods.PuntoFijo;
import content.methods.TableMethod;

import java.net.URL;
import java.util.ArrayList;

import java.util.Arrays;
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


    @FXML
    protected void showInfo(){
        String content =
                "BUILT IN OPERATORS: \n" +
                        "    Addition: 2 + 2\n" +
                        "    Subtraction: 2 - 2\n" +
                        "    Multiplication: 2 * 2\n" +
                        "    Division: 2 / 2\n" +
                        "    Exponentation: 2 ^ 2\n" +
                        "    Unary Minus,Plus (Sign Operators): +2 - (-2)\n" +
                        "    Modulo: 2 % 2\n\n"+
                        "BUILT IN FUNCTIONS: \n" +
                        "    abs: absolute value\n" +
                        "    acos: arc cosine\n" +
                        "    asin: arc sine\n" +
                        "    atan: arc tangent\n" +
                        "    cbrt: cubic root\n" +
                        "    ceil: nearest upper integer\n" +
                        "    cos: cosine\n" +
                        "    cosh: hyperbolic cosine\n" +
                        "    exp: euler's number raised to the power (e^x)\n" +
                        "    floor: nearest lower integer\n" +
                        "    log: logarithmus naturalis (base e)\n" +
                        "    log10: logarithm (base 10)\n" +
                        "    log2: logarithm (base 2)\n" +
                        "    sin: sine\n" +
                        "    sinh: hyperbolic sine\n" +
                        "    sqrt: square root\n" +
                        "    tan: tangent\n" +
                        "    tanh: hyperbolic tangent\n" +
                        "    signum: signum function";

        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("How to enter a equation");
        info.setHeaderText("Use the follow operators and functions: \n" +
                "Example: log(x)*sin(x)+(1/3)x");
        info.setContentText(content);
        info.show();
    }

    @FXML
    protected void calculateResult(){
        DrawView.drawEquation(lineChart,equationField,drawStart,drawEnd);
        UseMethod.calculateResult(methodTable,resultLabel,equationField,solveStart,solveEnd,errorField,methodBox,optionalObjects);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //create list for optional objects for different methods
        optionalObjects = new ArrayList<Object>();

        ObservableList<TableMethod> options =
                FXCollections.observableArrayList(
                        new Biseccion(),
                        new FalseRule(),
                        new PuntoFijo()
                );
        methodBox.setItems(options);


        reset();

        methodBox.valueProperty().addListener(new ChangeListener<TableMethod>() {
            @Override public void changed(ObservableValue value, TableMethod old, TableMethod newO) {
                //Show your scene here
                solveEnd.setDisable(false);
                toSolveLabel.setDisable(false);
                optionalObjects.clear();



                if(newO.getClass()==Biseccion.class){
                    optionalFields.getChildren().clear();

                }
                else if(newO.getClass()==FalseRule.class){
                    optionalFields.getChildren().clear();

                }
                else if(newO.getClass()==PuntoFijo.class){
                    solveEnd.setText("");
                    solveEnd.setDisable(true);
                    toSolveLabel.setDisable(true);


                    optionalFields.getChildren().clear();
                    HBox hBox = new HBox();
                    hBox.setSpacing(3);
                    hBox.getChildren().add(new Label("x="));
                    TextField textField = new TextField();
                    optionalObjects.add(textField);
                    hBox.getChildren().add(textField);
                    optionalFields.getChildren().add(hBox);
                }
            }
        });

        leftPane.maxWidthProperty().set(600);
        leftPane.minWidthProperty().set(10);
        centerContent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                splitPane.setDividerPosition(0,600);
            }
        });
    }

    public void draw(ActionEvent actionEvent) {
        DrawView.drawEquation(lineChart,equationField,drawStart,drawEnd);
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

        if(!lineChart.getTitle().equals(equationField.getText()))
            lineChart.setTitle(RESOURCE_FACTORY.getResources().getString("insertInput"));

    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }
}
