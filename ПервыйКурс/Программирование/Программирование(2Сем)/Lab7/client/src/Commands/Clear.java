package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;


    public Clear() {
        super("clear", false);
    }


    public Response execute(Request request) {
        return new Response("");
    }
}
