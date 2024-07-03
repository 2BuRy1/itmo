package Commands;

import java.io.Serial;
import java.io.Serializable;

public class ExecuteScript extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID= 20L;
    public ExecuteScript() {
        super("execute_script", true);
    }
}
