package sample.methods.model;

public class NewtonRapsonMultivarModel {
    String no, x, y, f1, f2, df1x, df1y, df2x, df2y, deltaX, deltaY, eX, eY;

    public NewtonRapsonMultivarModel(String no, String x, String y, String f1, String f2, String df1x, String df1y, String df2x, String df2y, String deltaX, String deltaY, String eX, String eY) {
        this.no = no;
        this.x = x;
        this.y = y;
        this.f1 = f1;
        this.f2 = f2;
        this.df1x = df1x;
        this.df1y = df1y;
        this.df2x = df2x;
        this.df2y = df2y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.eX = eX;
        this.eY = eY;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getDf1x() {
        return df1x;
    }

    public void setDf1x(String df1x) {
        this.df1x = df1x;
    }

    public String getDf1y() {
        return df1y;
    }

    public void setDf1y(String df1y) {
        this.df1y = df1y;
    }

    public String getDf2x() {
        return df2x;
    }

    public void setDf2x(String df2x) {
        this.df2x = df2x;
    }

    public String getDf2y() {
        return df2y;
    }

    public void setDf2y(String df2y) {
        this.df2y = df2y;
    }

    public String getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(String deltaX) {
        this.deltaX = deltaX;
    }

    public String getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(String deltaY) {
        this.deltaY = deltaY;
    }

    public String geteX() {
        return eX;
    }

    public void seteX(String eX) {
        this.eX = eX;
    }

    public String geteY() {
        return eY;
    }

    public void seteY(String eY) {
        this.eY = eY;
    }
}
