package Commands;

import Exceptions.AlreadyEmptyException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private final CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager) {
        super("clear");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.clear();
            return new Response("Коллекция успешно очищена!");
        } catch (AlreadyEmptyException e) {
            return new Response("Коллекция уже пуста");
        }
    }
}
