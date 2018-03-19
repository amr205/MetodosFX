package sample.methods.model;

import javafx.beans.property.SimpleStringProperty;

public class NewtonRaphsonModel {
    public final SimpleStringProperty n;
    public final SimpleStringProperty xi;
    public final SimpleStringProperty fxi;
    public final SimpleStringProperty dfxi;
    public final SimpleStringProperty xi2;
    public final SimpleStringProperty error;

    public NewtonRaphsonModel(String n, String xi, String fxi, String dfxi, String xi2, String error) {
        this.n =  new SimpleStringProperty(n);
        this.xi =  new SimpleStringProperty(xi);
        this.fxi = new SimpleStringProperty(fxi);
        this.dfxi = new SimpleStringProperty(dfxi);
        this.xi2 = new SimpleStringProperty(xi2);
        this.error = new SimpleStringProperty(error);
    }

    public String getN() {
        return n.get();
    }

    public void setN(String n) {
        this.n.set(n);
    }

    public String getXi() {
        return xi.get();
    }

    public void setXi(String xi) {
        this.xi.set(xi);
    }

    public String getFxi() {
        return fxi.get();
    }


    public void setFxi(String fxi) {
        this.fxi.set(fxi);
    }

    public String getDfxi() {
        return dfxi.get();
    }


    public void setDfxi(String dfxi) {
        this.dfxi.set(dfxi);
    }

    public String getXi2() {
        return xi2.get();
    }


    public void setXi2(String xi2) {
        this.xi2.set(xi2);
    }

    public String getError() {
        return error.get();
    }


    public void setError(String error) {
        this.error.set(error);
    }
}
