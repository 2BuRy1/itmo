package Entities;

public class NullPersonException extends RuntimeException {
private final String name;

    public NullPersonException(String name) {
    this.name = name;

    }
    @Override
    public String getMessage(){
        return "%sу не с кем летать".formatted(name);
    }
}
