package Commands;

import Exceptions.InvalidDataException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Add extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.add(request.getObject());
            return new Response("Объект успешно добавлен в коллекцию");
        } catch (InvalidDataException e) {
            return new Response("Поля объекта не валидны, он не добавлен в коллекци");
        }
    }
}
