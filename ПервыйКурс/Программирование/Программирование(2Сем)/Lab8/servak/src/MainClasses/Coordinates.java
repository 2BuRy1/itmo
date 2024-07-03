package MainClasses;

import Interfaces.Validatable;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс координат
 */
public class Coordinates implements Validatable, Serializable {
    @Serial
    private final static long serialVersionUID = 31L;
    private Double x; //Значение поля должно быть больше -964, Поле не может быть null
    private long y; //Значение поля должно быть больше -959

    public Coordinates(double x, long y){
        this.y = y;
        this.x=x;
    }
@Override
public String toString(){
        return x + " ; " + y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public boolean validate() {
        if(this.x <= -964 || x==null) return false;
        if((this.y<=-959)) return  false;
        return true;

    }
}
