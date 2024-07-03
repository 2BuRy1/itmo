package Commands;

import Managers.CollectionManager;import Exceptions.NoSuchElementException;

/**
 * Команда 'remove_by_id'
 * Удаление элемента коллекции по его id
 */
public class RemoveById extends Command {

    private final CollectionManager collectionManager;

    public RemoveById( CollectionManager collectionManager) {
        super("remove_by_id");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
public void execute(String args) {
        try {
            int id = Integer.parseInt(args.trim());
            collectionManager.removeElement(collectionManager.getById(id));
            System.out.println("Объект удален успешно!");

        } catch (NumberFormatException e) {
            System.err.println("id должно быть числом типа int");
            ;
        } catch (NoSuchElementException e) {
            System.err.println("Элемента с таким id не найдено");;
        }
}
}
