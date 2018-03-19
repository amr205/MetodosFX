package sample.methods;

import sample.Utilities.ObservableResourceFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TableMethod {
    ObservableResourceFactory RESOURCE_FACTORY;
    public TableMethod(ObservableResourceFactory RESOURCE_FACTORY){
        this.RESOURCE_FACTORY = RESOURCE_FACTORY;
    }
    public void initializeColumns(TableView table){}
    public float doMethod(TableView table, float aInicial, float bInicial, float errorP, String ecuacion, ArrayList<Object> list){return 0;}
    public void initializeOptional(VBox vBox, ArrayList<Object> optionalObjects){}
    public boolean validateOptional(ObservableResourceFactory RESOURCE_FACTORY, ArrayList<Object> optionalObjects){ return true;}
    public void showDescription(){
        System.out.println("description");
    };
}
