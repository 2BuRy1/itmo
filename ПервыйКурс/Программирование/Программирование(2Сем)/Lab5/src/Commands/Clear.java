package Commands;

import Managers.CollectionManager;
import Exceptions.AlreadyEmptyException;


/**
 * Команда 'clear'
 * Очищает содержимое коллекции
 */
public class Clear extends Command {

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager){
        super("clear");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
    public void execute(String args){
        try {
            System.out.println("Очистка коллекции");
            collectionManager.clear();
            System.out.println("Очистка прошла успешно");
        } catch (AlreadyEmptyException e) {
            System.err.println("Коллекция уже пуста");
            ;
        }
    }
}
