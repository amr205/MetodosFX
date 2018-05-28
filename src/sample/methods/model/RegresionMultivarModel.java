package sample.methods.model;

public class RegresionMultivarModel {
    String y,x1,x2,x12,x22,x1x2,x1y1,x2y1,st,sr;


    public RegresionMultivarModel(String y, String x1, String x2, String x12, String x22, String x1x2, String x1y1, String x2y1, String st, String sr) {
        this.y = y;
        this.x1 = x1;
        this.x2 = x2;
        this.x12 = x12;
        this.x22 = x22;
        this.x1x2 = x1x2;
        this.x1y1 = x1y1;
        this.x2y1 = x2y1;
        this.st = st;
        this.sr = sr;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public String getX12() {
        return x12;
    }

    public void setX12(String x12) {
        this.x12 = x12;
    }

    public String getX22() {
        return x22;
    }

    public void setX22(String x22) {
        this.x22 = x22;
    }

    public String getX1x2() {
        return x1x2;
    }

    public void setX1x2(String x1x2) {
        this.x1x2 = x1x2;
    }

    public String getX1y1() {
        return x1y1;
    }

    public void setX1y1(String x1y1) {
        this.x1y1 = x1y1;
    }

    public String getX2y1() {
        return x2y1;
    }

    public void setX2y1(String x2y1) {
        this.x2y1 = x2y1;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }
}
