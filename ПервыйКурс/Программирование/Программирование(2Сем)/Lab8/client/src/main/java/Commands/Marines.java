package Commands;

import java.io.Serializable;

public class Marines extends Command implements Serializable {
    private final static long serialVersionUID = 42L;

    public Marines() {
        super("marines", false);
    }
}
