package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class MaxByChapter extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;

    public MaxByChapter() {
        super("max_by_chapter", false);
    }


}
