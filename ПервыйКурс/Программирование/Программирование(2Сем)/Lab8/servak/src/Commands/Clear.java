package Commands;

import Exceptions.AlreadyEmptyException;
import MainClasses.SpaceMarine;
import Managers.CollectionManager;
import Managers.DataBaseManager;
import Network.Request;
import Network.Response;

import javax.xml.crypto.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Clear extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 3L;
    private CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager, DataBaseManager dataBaseManager) {
        super("clear");
        this.collectionManager = collectionManager;

    }

    @Override
    public Response execute(Request request, CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        DataBaseManager dataBaseManager = new DataBaseManager();
        List<Long> ids = collectionManager.AllMarines().stream().
                filter(spaceMarine -> spaceMarine.getUserLogin().equals(request.getUser().getLogin())).map(SpaceMarine::getId).toList();
        if(dataBaseManager.deleteUserObjects(request.getUser() ,ids)) {
            collectionManager.removeElements(ids);
            return new Response("Коллекция успешно очищена!");
        }
        return new Response("Коллекция не очищена, тк в ней нет ваших объектов");
    }
}
