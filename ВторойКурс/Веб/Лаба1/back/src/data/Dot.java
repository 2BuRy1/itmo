package data;

public class Dot extends RequestData {

    private final int x;
    private final double y;
    private final int r;

    private  boolean isValidData;


    public Dot(int x, double y, int r, boolean isValidData) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isValidData = isValidData;
    }





    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isValidData() {
        return isValidData;
    }



    public void setValidData(boolean validData) {
        isValidData = validData;
    }
}
