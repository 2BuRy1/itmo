package Entities;

public class FlyException extends Exception {
    private final String name;

    public FlyException(String name){
    this.name = name;

    }
    @Override
    public String getMessage() {
        return "Персонаж : %s не может взлететь".formatted(name) ;


    }
}

