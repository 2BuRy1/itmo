package Commands;

import Managers.CollectionManager;
import Exceptions.EmptyCollectionException;

/**
 * Команда 'print_field_ascending_category'
 * Выводит значенияполя category в порядке возрастания для всех элементов коллекции
 */
public class PrintFieldAscendingCategory extends Command {
private final CollectionManager collectionManager;

public PrintFieldAscendingCategory(CollectionManager collectionManager){
    super("print_field_ascending_category");
    this.collectionManager=collectionManager;
}

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
    public void execute(String args)  {
        try{
            collectionManager.printFieldAscendingCategory();
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста");;
        }

    }
}
