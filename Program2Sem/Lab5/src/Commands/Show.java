package Commands;

import Managers.CollectionManager;
import Exceptions.EmptyCollectionException;

/**
 * Команда 'Show'
 * Выводит все содержимое коллекции
 */
public class Show extends Command {

private final CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        super("show");
        this.collectionManager=collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
    @Override
    public  void execute(String args){

    try{
        collectionManager.show();

        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста");
        }
    }
}
