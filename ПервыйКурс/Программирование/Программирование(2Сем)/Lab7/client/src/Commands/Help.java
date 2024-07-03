package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Help extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 8L;
    public Help() {
        super("help", false);
    }


}
