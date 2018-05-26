package sample.methods2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.JacobiOtputController;
import sample.controller.methods.NewtonRMInputController;
import sample.controller.methods.RegresionLinealInputController;
import sample.methods.model.NewtonRapsonMultivarModel;

public class RegresionLineal extends ParentMethod{
    protected RegresionLinealInputController inputController;
    protected JacobiOtputController outputController;

    public RegresionLineal(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionLinealInput.fxml"));
            Parent inputRoot =  inputLoader.load();
            RegresionLinealInputController inputController = (RegresionLinealInputController) inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/JacobiOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            JacobiOtputController outputController = (JacobiOtputController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);



            this.inputController = inputController;
            this.outputController = outputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void initTable(){
        TableView table = outputController.getTable();
        table.getItems().clear();
        table.getColumns().clear();

        TableColumn column = new TableColumn("no");
        column.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("no"));

        TableColumn column1 = new TableColumn("x");
        column1.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("x"));

        TableColumn column2 = new TableColumn("y");
        column2.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("y"));

        TableColumn column3 = new TableColumn("f1");
        column3.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("f1"));

        TableColumn column4 = new TableColumn("f2");
        column4.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("f2"));

        TableColumn column5 = new TableColumn("df1x");
        column5.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df1x"));

        TableColumn column6 = new TableColumn("df1y");
        column6.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df1y"));

        TableColumn column7 = new TableColumn("df2x");
        column7.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df2x"));

        TableColumn column8 = new TableColumn("df2y");
        column8.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df2y"));

        TableColumn column9 = new TableColumn("deltaX");
        column9.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("deltaX"));

        TableColumn column10 = new TableColumn("deltaY");
        column10.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("deltaY"));

        TableColumn column11 = new TableColumn("error X");
        column11.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("eX"));

        TableColumn column12 = new TableColumn("error Y");
        column12.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("eY"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3,column4, column5, column6, column7, column8,column9,column10,column11,column12);

    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descGaussHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descGaussDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }




    @Override
    public String toString(){
        return "Regresion lineal";
    }
}
