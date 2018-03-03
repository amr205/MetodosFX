package content.methods.model;

import javafx.beans.property.SimpleStringProperty;

public class BiseccionModel {
    public final SimpleStringProperty n;
    public final SimpleStringProperty a;
    public final SimpleStringProperty b;
    public final SimpleStringProperty fa;
    public final SimpleStringProperty fb;
    public final SimpleStringProperty xr;
    public final SimpleStringProperty fxr;
    public final SimpleStringProperty error;

    public BiseccionModel(String _n, String _a, String _b, String _fa, String _fb, String _xr, String _fxr, String _error) {
        this.n = new SimpleStringProperty(_n);
        this.a = new SimpleStringProperty(_a);
        this.b = new SimpleStringProperty(_b);
        this.fa = new SimpleStringProperty(_fa);
        this.fb = new SimpleStringProperty(_fb);
        this.xr = new SimpleStringProperty(_xr);
        this.fxr = new SimpleStringProperty(_fxr);
        this.error = new SimpleStringProperty(_error);
    }

    public String getA() {
        return a.get();
    }

    public String getB() {
        return b.get();
    }

    public String getError() {
        return error.get();
    }

    public String getFa() {
        return fa.get();
    }

    public String getFb() {
        return fb.get();
    }

    public String getFxr() {
        return fxr.get();
    }

    public String getN() {
        return n.get();
    }

    public String getXr() {
        return xr.get();
    }
}
