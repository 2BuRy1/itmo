package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    public Show() {
        super("show", false);
    }


}
