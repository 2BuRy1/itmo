package Commands;

import Managers.CollectionManager;


/**
 * Команда 'Sort'
 * Позволяет сортировать коллекцию в естественном порядке
 */
public class Sort extends Command {
private final CollectionManager collectionManager;
public Sort(CollectionManager collectionManager){
    super("sort");
    this.collectionManager=collectionManager;
}

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
    public void execute(String args){
        System.out.println("Выполняется сортировка коллекции..");
        collectionManager.sort();
        System.err.println("Сортировка выполнена успешно!");
    }
}
