package content.methods.model;

import javafx.beans.property.SimpleStringProperty;

public class SecanteModel {
    public final SimpleStringProperty n;
    public final SimpleStringProperty x0;
    public final SimpleStringProperty xi;
    public final SimpleStringProperty fx0;
    public final SimpleStringProperty fxi;
    public final SimpleStringProperty x2;
    public final SimpleStringProperty error;

    public SecanteModel(String n, String x0, String xi, String fx0, String fxi, String x2, String error) {
        this.n = new SimpleStringProperty(n);
        this.x0 = new SimpleStringProperty(x0);
        this.xi = new SimpleStringProperty(xi);
        this.fx0 = new SimpleStringProperty(fx0);
        this.fxi = new SimpleStringProperty(fxi);
        this.x2 = new SimpleStringProperty(x2);
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

    public String getXi() {
        return xi.get();
    }



    public void setXi(String xi) {
        this.xi.set(xi);
    }

    public String getFx0() {
        return fx0.get();
    }


    public void setFx0(String fx0) {
        this.fx0.set(fx0);
    }

    public String getFxi() {
        return fxi.get();
    }



    public void setFxi(String fxi) {
        this.fxi.set(fxi);
    }

    public String getX2() {
        return x2.get();
    }


    public void setX2(String x2) {
        this.x2.set(x2);
    }

    public String getError() {
        return error.get();
    }


    public void setError(String error) {
        this.error.set(error);
    }
}