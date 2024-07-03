package Commands;

import Enums.MeleeWeapon;
import Exceptions.EmptyCollectionException;
import Managers.CollectionManager;
import Network.Request;
import Network.Response;

import java.io.Serial;
import java.io.Serializable;

public class CountLess extends Command implements Serializable {

    private final CollectionManager collectionManager;
    @Serial
    private static final long serialVersionUID  = 14L;


    public CountLess(CollectionManager collectionManager) {
        super("count_less_than_melee_weapon");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        try {
            return collectionManager.countByMeleeWeapon(MeleeWeapon.valueOf((String) request.getArgs()));
        } catch (EmptyCollectionException e) {
            return new Response("Коллекция пуста");
        }
    }
}
