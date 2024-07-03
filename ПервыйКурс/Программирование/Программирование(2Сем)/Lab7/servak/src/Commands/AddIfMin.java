package Commands;

import Exceptions.EmptyCollectionException;
import Exceptions.InvalidDataException;
import Exceptions.NotMinElementException;
import MainClasses.SpaceMarine;
import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class AddIfMin extends Command implements Serializable {
    private final DataBaseManager dataBaseManager;

    @Serial
    private static final long serialVersionUID = 9L;

    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager, DataBaseManager dataBaseManager) {
        super("add_if_min");
        this.collectionManager = collectionManager;
        this.dataBaseManager = dataBaseManager;

    }

    @Override
    public Response execute(Request request) {
        try {
            SpaceMarine spaceMarine = request.getObject();
            int id = dataBaseManager.addmarines(spaceMarine, request.getUser());
            if (collectionManager.addIfMin(request.getObject()) || id == -1) {
                return new Response("Не удалось добавить объект:(");
            }
            request.spaceMarine.setId((long) id);
            collectionManager.add(spaceMarine);
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
