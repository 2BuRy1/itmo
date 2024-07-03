package Commands;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class  Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final String name;

    private final boolean hasArguments;

    public Command(String name, boolean hasArguments) {
        this.name = name;
        this.hasArguments = hasArguments;

    }


    public String getName() {
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

    public boolean isHasArguments() {
        return this.hasArguments;
    }
}



