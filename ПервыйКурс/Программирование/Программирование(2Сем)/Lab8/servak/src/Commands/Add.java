package Commands;

import Exceptions.InvalidDataException;
import MainClasses.SpaceMarine;
import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.Request;
import Network.Response;

import javax.xml.crypto.Data;
import java.io.Serial;
import java.io.Serializable;

public class Add extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;
    private CollectionManager collectionManager;

    public Add(CollectionManager collectionManager, DataBaseManager dataBaseManager) {
        super("add");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        try {
            DataBaseManager dataBaseManager = new DataBaseManager();
            this.collectionManager = collectionManager;
            SpaceMarine spaceMarine= request.getObject();
            int id = dataBaseManager.addmarines(spaceMarine, request.getUser());
            if(id == -1){
                return new Response("Не удалось добавить объект:(");
            }
            request.spaceMarine.setId((long) id);
            collectionManager.add(spaceMarine);
            return new Response("Объект успешно добавлен в коллекцию");
        } catch (InvalidDataException e) {
            return new Response("Поля объекта не валидны, он не добавлен в коллекци");
        }
    }
}
