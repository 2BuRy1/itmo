package Commands;

import Exceptions.NoSuchElementException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Remove extends Command implements Serializable {


    private final CollectionManager collectionManager;
    @Serial
    private final static long serialVersionUID = 11L;
    public Remove(CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.removeElement(collectionManager.getById((Integer) request.getArgs()));
            return new Response("Элемент успешно удален");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с таким id нет в коллекции");
        }
    }
}
