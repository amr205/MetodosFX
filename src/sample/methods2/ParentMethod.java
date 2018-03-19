package sample.methods2;

import sample.Utilities.ObservableResourceFactory;
import javafx.scene.layout.AnchorPane;

public class ParentMethod {
    protected final ObservableResourceFactory RESOURCE_FACTORY;

    public ParentMethod(ObservableResourceFactory RESOURCE_FACTORY){
        this.RESOURCE_FACTORY = RESOURCE_FACTORY;
    }
    public void initialize(AnchorPane inputSection, AnchorPane outputSection){

    }

    public void showInfo(){

    }
}
