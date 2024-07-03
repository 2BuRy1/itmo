package Commands;

import Exceptions.EmptyCollectionException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class PrintFieldAscendingCategory extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    private final CollectionManager collectionManager;
    public PrintFieldAscendingCategory(CollectionManager collectionManager) {
        super("print_field_ascending_category");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            return (collectionManager.printFieldAscendingCategory());
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста");
        }
    }
}
