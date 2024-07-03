package Commands;

import Builders.SpaceMarineBuilder;
import Managers.CollectionManager;
import Exceptions.EmptyCollectionException;
import Exceptions.InvalidDataException;

/**
 * Команды 'insert_at'
 * Вставляет новый элемент в заданную позицию в коллекции
 */
public class InsertAtIndex  extends Command {

    private final CollectionManager collectionManager;

    public InsertAtIndex(CollectionManager collectionManager) {
        super("insert_at");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команы
     * Метод запуска команды
     */
    @Override
    public void execute(String args) {
        try {
            int id = Integer.parseInt(args.trim());
            collectionManager.insertAtIndex(id, new SpaceMarineBuilder().create());
            System.out.println("Объект добавлен успешно");
        } catch (InvalidDataException e) {
            System.err.println("Поля введены некорректно, объект не создан");
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста");;
        }
    }
}
