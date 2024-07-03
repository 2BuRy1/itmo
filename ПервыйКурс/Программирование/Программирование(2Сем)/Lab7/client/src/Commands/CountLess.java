package Commands;

import java.io.Serial;
import java.io.Serializable;

public class CountLess extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 14L;
    public CountLess() {
        super("count_less_than_melee_weapon", true);
    }
}
