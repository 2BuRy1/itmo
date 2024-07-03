package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Update extends Command implements Serializable {

@Serial
private final static long serialVersionUID = 10L;

    public Update() {
        super("update", true);
    }


}
