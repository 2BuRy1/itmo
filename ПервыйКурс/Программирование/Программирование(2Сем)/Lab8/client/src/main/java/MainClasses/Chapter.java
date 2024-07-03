package MainClasses;


import java.io.Serial;
import java.io.Serializable;

/**
 * Класс отдельной части в космосе
 */
public class Chapter implements Validatable, Serializable {


    @Serial
    private final static long serialVersionUID = 30L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    public Chapter(String name, int marinesCount){
        this.name=name;
        this.marinesCount=marinesCount;
    }
    @Override
    public String toString(){
        return (name!=null ? name : "") + " " +(marinesCount > 0 ? marinesCount : "");
    }

    @Override
    public boolean validate() {
        if(this.name == null || this.name.isEmpty()) return false;
        if((this.marinesCount <=0 || this.marinesCount > 1001)) return false;
        return true;
    }


    public int getMarinesCount(){
        return this.marinesCount;
    }

    public String getName(){
        return this.name;
    }
}
