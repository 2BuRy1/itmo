package Commands;

import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class PrintFieldAscendingCategory extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;

    public PrintFieldAscendingCategory() {
        super("print_field_ascending_category", false);
    }




}
