package Commands;

import Exceptions.EmptyCollectionException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.security.interfaces.RSAKey;

public class Insert extends Command implements Serializable {

    private CollectionManager collectionManager;
    @Serial
    private final static long serialVersionUID = 11L;

    public Insert(CollectionManager collectionManager) {
        super("insert_at");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        try {
            collectionManager.insertAtIndex((Integer) request.getArgs(), request.getObject());
            return new Response("Объект успешно добавлен в позицию %d".formatted(request.getArgs()));
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста");
        }
    }
}
