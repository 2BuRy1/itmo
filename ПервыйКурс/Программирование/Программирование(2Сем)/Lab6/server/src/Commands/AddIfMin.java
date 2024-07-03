package Commands;

import Exceptions.EmptyCollectionException;
import Exceptions.InvalidDataException;
import Exceptions.NotMinElementException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class AddIfMin extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 9L;

    private final CollectionManager collectionManager;
    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min");
        this.collectionManager = collectionManager;

    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.addIfMin(request.getObject());
            return new Response("Объект был успешно добавлен в коллекцию!");
        } catch (InvalidDataException e) {
            return new Response("Поля объекта не валидны, он не был добавлен в коллекцию");
        } catch (EmptyCollectionException e) {
             return new Response("Коллекция пуста, не с чем сравнивать:(");
        } catch (NotMinElementException e) {
            return new Response("Элемент не является минимальным, он не был добавлен в коллекцию");
        }
    }
}
