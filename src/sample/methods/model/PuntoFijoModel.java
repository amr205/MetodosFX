package sample.methods.model;

import javafx.beans.property.SimpleStringProperty;

public class PuntoFijoModel {
    public final SimpleStringProperty n;
    public final SimpleStringProperty x0;
    public final SimpleStringProperty x1;
    public final SimpleStringProperty error;

    public PuntoFijoModel(String n, String x0, String x1, String error) {
        this.n = new SimpleStringProperty(n);
        this.x0 = new SimpleStringProperty(x0);
        this.x1 = new SimpleStringProperty(x1);
        this.error = new SimpleStringProperty(error);
    }

    public String getN() {
        return n.get();
    }


    public void setN(String n) {
        this.n.set(n);
    }

    public String getX0() {
        return x0.get();
    }


    public void setX0(String x0) {
        this.x0.set(x0);
    }

    public String getX1() {
        return x1.get();
    }


    public void setX1(String x1) {
        this.x1.set(x1);
    }

    public String getError() {
        return error.get();
    }

    public void setError(String error) {
        this.error.set(error);
    }
}