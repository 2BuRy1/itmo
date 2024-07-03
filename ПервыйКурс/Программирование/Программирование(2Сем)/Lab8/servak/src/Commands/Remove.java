package Commands;

import Exceptions.NoSuchElementException;
import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Remove extends Command implements Serializable {

    private CollectionManager collectionManager;
    @Serial
    private final static long serialVersionUID = 11L;
    public Remove(CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        DataBaseManager dataBaseManager = new DataBaseManager();
        this.collectionManager = collectionManager;
        try {
            if (dataBaseManager.deleteObject((Long) request.getArgs(), request.getUser())){
                collectionManager.removeElement(collectionManager.getById((Long) request.getArgs()));
                return new Response("Элемент успешно удален");
            }
            return new Response("Не удалось удалить еэлемент, тк он не принадлежит данному пользователю");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с таким id нет в коллекции");
        }
    }
}
