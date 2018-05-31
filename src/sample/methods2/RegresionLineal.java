package sample.methods2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import sample.controller.methods.*;
import sample.methods.model.NewtonRapsonMultivarModel;
import sample.methods.model.RegresionLinealModel1;
import sample.methods.model.RegresionLinealModel2;

//se encarga de realizar el método y mostrar la información en pantalla

public class RegresionLineal extends ParentMethod{
    protected RegresionLinealInputController inputController;
    protected RegresionLinealOutputController outputController;

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

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionLinealOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            RegresionLinealOutputController outputController = (RegresionLinealOutputController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setRegresionLineal(this);

            this.inputController = inputController;
            this.outputController = outputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void solve(float [] x,float [] y, int n,float X){
        TableView table1 = outputController.getTable1();
        TableView table2 = outputController.getTable2();

        ObservableList<RegresionLinealModel1> model1 = FXCollections.observableArrayList();
        ObservableList<RegresionLinealModel2> model2 = FXCollections.observableArrayList();



        float [][] values=new float[n][4];
        String ecu;
        float sx=0,sy=0,sxy=0,sx2=0,st=0,sr=0,xm=0,ym=0,a1,a0,r=0,Y=0;

        for(int i=0;i<n;i++){
            sy+=y[i];
            sx+=x[i];
            values[i][0]=x[i]*y[i];
            sxy+=x[i]*y[i];
            values[i][1]=x[i]*x[i];
            sx2+=x[i]*x[i];

            model1.add(new RegresionLinealModel1(""+x[i],""+y[i],""+values[i][0],""+ values[i][1]));

        }

        model1.add(new RegresionLinealModel1("Suma "+sx,"Suma "+sy,"Suma "+sxy,"Suma "+sx2));


        xm=sx/n;
        ym=sy/n;

        a1=(n*sxy-sx*sy)/(n*sx2-(sx*sx));
        a0=ym-a1*xm;

        ecu="y="+a0+" + "+a1+"x";
        System.out.println(ecu);
        inputController.setEquation(ecu);
        Y=a0+a1*X;
        System.out.println(Y);
        inputController.setY(""+Y);

        for(int j=0;j<n;j++){

            values[j][2]=(y[j]-ym)*(y[j]-ym);
            st+=(y[j]-ym)*(y[j]-ym);
            values[j][3]=(y[j]-a0-a1*x[j])*(y[j]-a0-a1*x[j]);
            sr+=(y[j]-a0-a1*x[j])*(y[j]-a0-a1*x[j]);

            model2.add(new RegresionLinealModel2(""+values[j][2],""+values[j][3]));

        }

        model2.add(new RegresionLinealModel2("Suma "+st,"Suma "+sr));


        r=(float)Math.sqrt((st-sr)/st);
        System.out.println(r);
        inputController.setR(""+r);

        table1.setItems(model1);
        table2.setItems(model2);



    }

    public void initTable(){

        TableView table1 = outputController.getTable1();
        TableView table2 = outputController.getTable2();

        table1.getItems().clear();
        table1.getColumns().clear();

        table2.getItems().clear();
        table2.getColumns().clear();

    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Regresion lineal");
        info.setHeaderText("Descripción");
        info.setContentText("En este método, se toman en cuenta todos los datos, y mediante el uso de la formula especifica se logran ajustar dichos datos a una función, en este caso lineal, función con la cual se pueden predecir datos con los que originalmente no contábamos");
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }




    @Override
    public String toString(){
        return "Regresion lineal";
    }
}
