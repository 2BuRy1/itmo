package Commands;



import Interfaces.Executor;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс для всех команд, являющийся абстракцией
 */
public abstract class Command implements Executor, Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
private final String name;



public Command(String name){
    this.name=name;

}


public String getName(){
    return this.name;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract Response execute(Request request, CollectionManager collectionManager);
}
