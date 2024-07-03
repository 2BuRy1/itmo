package Commands;

import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serializable;

public class AllMarines extends Command implements Serializable {

    private final static long serialVersionUID = 128L;

    private CollectionManager collectionManager;

    public AllMarines() {
        super("AllMarines");
    }


    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        return new Response(collectionManager.AllMarines());
    }
}
