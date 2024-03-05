package Commands;

import Builders.SpaceMarineBuilder;
import Managers.CollectionManager;
import Exceptions.InvalidDataException;

/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class Add extends Command {

    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add");
        this.collectionManager = collectionManager;
    }


    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
    public void execute(String args) {
        try {
            System.out.println("Создание объекта Spacemarine");
            collectionManager.add(new SpaceMarineBuilder().create());
            System.out.println("Объект создан успешно!");
        } catch (InvalidDataException e) {
            System.err.println("Поля введены некорректно, объект не создан");;
        }

    }


}





