package Commands;

import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serializable;

public class Marines extends Command implements Serializable {
    private final static long serialVersionUID = 42L;

    public Marines() {
        super("marines");
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        return new Response(collectionManager.getMarines(request.getUser()));
    }
}
