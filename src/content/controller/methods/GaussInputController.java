package content.controller.methods;

import content.Main;
import content.Utilities.ObservableResourceFactory;
import content.methods2.Gauss;
import content.resources.lang.Resources;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class GaussInputController implements Initializable{

    @FXML
    Label numberOfEquationsLabel;
    @FXML
    VBox resultBox;
    @FXML
    Button numberButton, calculateBtn;

    @FXML
    AnchorPane bottomPane, mainPane;

    @FXML
    TextField numberField;

    private final Preferences prefs = Preferences.userNodeForPackage(Main.class);
    private String RESOURCE_NAME;
    private ObservableResourceFactory RESOURCE_FACTORY;



    private double[][] equationsCoeficients;
    private double[] equationsEqualities;
    private Object[][] equationsField;
    private int n;
    private Gauss gauss;
    private boolean areFields;
    private String currentText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RESOURCE_FACTORY = Main.RESOURCE_FACTORY;
        RESOURCE_NAME = Main.RESOURCE_NAME;

        n=0;
        areFields = false;
        currentText = "";

        bindText();
        //updateLanguage();
    }

    public void updateLanguage() {
        String defaultLang = prefs.get("DEFAULT_LANGUAGE","es");

        if(defaultLang.equals("es"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.forLanguageTag("es")));

        else if(defaultLang.equals("en"))
            RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, Locale.ROOT));

    }

    private void bindText(){
        calculateBtn.textProperty().bind(RESOURCE_FACTORY.getStringBinding("calculateBtn"));
        numberOfEquationsLabel.textProperty().bind(RESOURCE_FACTORY.getStringBinding("numberOfEquationsLabel"));
    }

    @FXML
    public void createFields() {
        try{
            n = Integer.parseInt(numberField.getText());

            if(n<2){
                //TODO alert for negative number of equations
                return;
            }
        }catch (Exception e){
            //TODO alert for check you are entering only numbers
            return;
        }
        bottomPane.getChildren().clear();
        equationsCoeficients = new double[n][n];
        equationsEqualities = new double[n];
        equationsField = new Object[n][n+1];

        ScrollPane root = new ScrollPane();
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setPadding(new Insets(10));


        VBox box = new VBox(10);
        for(int i=0;i<n;i++)
            box.getChildren().addAll(getEquationRow(equationsField,i), new Separator());

        root.setContent(box);
        root.setPrefWidth(570);
        root.setPrefHeight(600);
        bottomPane.getChildren().setAll(root);
        areFields=true;
        currentText = numberField.getText();
    }

    public Parent getEquationRow(Object[][] array, int y){
        HBox row = new HBox(5);
        row.setMinWidth(550);
        for(int i=0;i<n+1;i++){
            TextField temp = new TextField();
            temp.setPrefWidth(43);
            array[y][i]=temp;
            row.getChildren().addAll(temp);
            if(i<(n-1))
                row.getChildren().addAll(new Label("x"+(i+1)+" +"));
            else if(i<n)
                row.getChildren().add(new Label("x"+(i+1)+" ="));
        }
        return row;
    }

    public void calculate(ActionEvent actionEvent) {
        if(areFields&&currentText.equals(numberField.getText())){
            try {
                for (int y = 0; y < equationsField.length; y++) {
                    for (int x = 0; x < equationsField[y].length; x++) {
                        if (x == equationsField[y].length - 1) {
                            equationsEqualities[y] = Double.parseDouble(((TextField)equationsField[y][x]).getText());
                            System.out.print(""+Double.parseDouble(((TextField)equationsField[y][x]).getText()));
                        } else {
                            equationsCoeficients[y][x] = Double.parseDouble(((TextField)equationsField[y][x]).getText());
                            System.out.print(""+Double.parseDouble(((TextField)equationsField[y][x]).getText()));

                        }
                    }
                    System.out.println("\n");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                //TODO ALERT for not entering right the equations

            }

            gauss.solve(equationsCoeficients,equationsEqualities);
        }
        else
            createFields();
    }

    public void startResult(){
        resultBox.getChildren().clear();
        Label label = new Label();
        label.textProperty().bind(RESOURCE_FACTORY.getStringBinding("resultLabelText"));
        label.setFont(new Font(20));
        label.setPrefHeight(40);
        resultBox.getChildren().add(label);
    }
    public void addResultLabel(Double value, int i){
        Label label = new Label();
        label.setText("     x"+i+" = "+String.format("%.6f",value));
        label.setFont(new Font(16));
        resultBox.getChildren().add(label);
    }


    public void setGauss(Gauss gauss) {
        this.gauss = gauss;
    }


    //TODO make style stay between scenes
}
