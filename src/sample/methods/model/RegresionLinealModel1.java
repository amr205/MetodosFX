package sample.methods.model;

public class RegresionLinealModel1 {
    String x, y, xy, x2;

    public RegresionLinealModel1(String x, String y, String xy, String x2) {
        this.x = x;
        this.y = y;
        this.xy = xy;
        this.x2 = x2;
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

    public String getXy() {
        return xy;
    }

    public void setXy(String xy) {
        this.xy = xy;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }
}
