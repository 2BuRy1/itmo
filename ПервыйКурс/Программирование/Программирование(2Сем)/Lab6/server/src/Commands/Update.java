package Commands;

import Exceptions.InvalidDataException;
import Exceptions.NoSuchElementException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Update extends Command implements Serializable {

    private final CollectionManager collectionManager;
    @Serial
    private final static long serialVersionUID = 10L;

    public Update(CollectionManager collectionManager) {
        super("update");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            collectionManager.updateByID((Integer) request.getArgs(), request.getObject());
            return new Response("Объект успешно обновлен");
        } catch (InvalidDataException e) {
            return new Response("Вы ввели некорректные данные, объект не был обновлен");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с заданным id нет в коллекции");

        }
    }
}
