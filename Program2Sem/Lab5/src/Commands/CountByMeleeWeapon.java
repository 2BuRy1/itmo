package Commands;

import Managers.CollectionManager;
import Enums.MeleeWeapon;
import Exceptions.EmptyCollectionException;

/**
 * Команда 'count_less_than_melee_weapon'
 * Посчитает количество объектов, поле meleeweapon у которых меньше заданного
 */
public class CountByMeleeWeapon extends Command {
    private final CollectionManager collectionManager;

    public CountByMeleeWeapon(CollectionManager collectionManager) {
        super("count_less_than_melee_weapon");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
    @Override
    public void execute(String args) {
        try {
            MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(args.trim().toUpperCase());
           collectionManager.countByMeleeWeapon(meleeWeapon);


        } catch (IllegalArgumentException ex) {
            System.err.println("Такого ближнего оружия нет");

        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста");;
        }
    }
}
