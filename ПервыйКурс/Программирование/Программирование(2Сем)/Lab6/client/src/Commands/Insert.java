package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Insert  extends Command implements Serializable {
    @Serial
    private final static long serialVersionUID = 11L;

    public Insert() {
        super("insert_at", true);
    }


}
