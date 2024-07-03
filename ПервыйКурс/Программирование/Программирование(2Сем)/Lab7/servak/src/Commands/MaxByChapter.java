package Commands;

import Exceptions.EmptyCollectionException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class MaxByChapter extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;

    private final CollectionManager collectionManager;

    public MaxByChapter(CollectionManager collectionManager) {
        super("max_by_chapter");
        this.collectionManager=collectionManager;
    }

    @Override
    public Response execute(Request request) {
        return (collectionManager.maxByChapter());

    }
}
