package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Remove extends Command implements Serializable {



    @Serial
    private final static long serialVersionUID = 11L;
    public Remove() {
        super("remove_by_id", true);
    }


}
