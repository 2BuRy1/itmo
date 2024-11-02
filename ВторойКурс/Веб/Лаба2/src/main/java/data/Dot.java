package data;

public class Dot{

    private final double x;
    private final double y;
    private final double r;

    private boolean status;


    public Dot(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }





    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isStatus() {
        return status;
    }

    public void status(boolean inTheGraphic) {
        status = inTheGraphic;
    }
}