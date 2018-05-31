package sample.methods2;

import sample.Utilities.ObservableResourceFactory;
import javafx.scene.layout.AnchorPane;

//clase de la cual los métodos heredan, en initialize tienen que cargar las secciones de entrada y salida, y en showInfo mostrar la descripción

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
