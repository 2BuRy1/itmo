package Commands;

import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Help extends  Command implements Serializable {

    private final CollectionManager collectionManager;

    @Serial
    private static final long serialVersionUID = 8l;
    public Help(CollectionManager collectionManager) {
        super("help");
        this.collectionManager=collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return (collectionManager.help());
    }
}
