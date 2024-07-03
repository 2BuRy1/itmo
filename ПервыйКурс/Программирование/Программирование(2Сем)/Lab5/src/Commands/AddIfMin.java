package Commands;

import Builders.SpaceMarineBuilder;
import Managers.CollectionManager;
import Exceptions.EmptyCollectionException;
import Exceptions.InvalidDataException;


/**
 * Команда 'add_if_min'
 * Добавляет новый элемент в коллекцию, если значение его поля наименьшее
 */
public class AddIfMin extends Command {

    private final CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
  @Override
  public  void execute(String args) {
        try {
            System.out.println("Добавление объекта с минимальным marinesCount");
            collectionManager.add_if_min(new SpaceMarineBuilder().create());
        } catch (InvalidDataException e) {
            System.err.println("Поля объекта не валидны");
        } catch (EmptyCollectionException e) {
            System.err.println("Невозможно добавить элемент, так как коллекция пуста");
        }
  }
}
