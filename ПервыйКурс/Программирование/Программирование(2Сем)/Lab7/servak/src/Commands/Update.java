package Commands;

import Exceptions.InvalidDataException;
import Exceptions.NoSuchElementException;
import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.Request;
import Network.Response;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Update extends Command implements Serializable {

    private final DataBaseManager dataBaseManager;

    private final CollectionManager collectionManager;
    @Serial
    private final static long serialVersionUID = 10L;

    public Update(CollectionManager collectionManager, DataBaseManager dataBaseManager) {
        super("update");
        this.collectionManager = collectionManager;
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            if(dataBaseManager.updateObject((Long)request.getArgs(), request.getUser(), request.getObject())) {
                collectionManager.updateByID((Long) request.getArgs(), request.getObject());
                return new Response("Объект успешно обновлен");
            }
            return new Response("Не удалось удалить объект");
        } catch (InvalidDataException e) {
            return new Response("Вы ввели некорректные данные, объект не был обновлен");
        } catch (NoSuchElementException e) {
            return new Response("Элемента с заданным id нет в коллекции");

        }
    }
}
