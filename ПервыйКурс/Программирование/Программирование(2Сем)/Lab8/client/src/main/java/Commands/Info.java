package Commands;


import java.io.Serial;
import java.io.Serializable;

public class Info extends Commands.Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Info() {
        super("info", false);
    }


}
