package Commands;

import java.io.Serial;
import java.io.Serializable;

public class Login extends Command implements Serializable {

    @Serial
    private final static long serialVersionUID = 25L;


    public Login( ) {
        super("login", false);
    }
}
