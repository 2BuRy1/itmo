package MainClasses;

import Interfaces.Validatable;

/**
 * Класс координат
 */
public class Coordinates implements Validatable {
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


    @Override
    public boolean validate() {
        if(this.x <= -964 || x==null) return false;
        if((this.y<=-959)) return  false;
        return true;

    }
}
