package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class AddIfMin extends Command implements Serializable {

    @Serial
    private final static long serialVersionUID = 9L;


    public AddIfMin() {
        super("add_if_min", false);
    }


}
