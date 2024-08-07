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
    private final DataBaseManager dataBaseManager;
    private final CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager, DataBaseManager dataBaseManager) {
        super("clear");
        this.collectionManager = collectionManager;
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public Response execute(Request request) {
        List<Long> ids = collectionManager.getMarines().stream().
                filter(spaceMarine -> spaceMarine.getUserLogin().equals(request.getUser().getLogin())).map(SpaceMarine::getId).toList();
        if(dataBaseManager.deleteUserObjects(request.getUser() ,ids)) {
            collectionManager.removeElements(ids);
            return new Response("Коллекция успешно очищена!");
        }
        return new Response("Коллекция не очищена, тк в ней нет ваших объектов");
    }
}
