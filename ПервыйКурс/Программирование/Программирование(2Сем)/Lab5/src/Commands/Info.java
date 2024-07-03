package Commands;

import Managers.CollectionManager;

/**
 * Команда 'info'
 * Выводит основную информацию о коллекции
 */
public class Info extends Command {

    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager){
        super("info");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
    public void execute(String args){
        collectionManager.getInfo();
    }
}
