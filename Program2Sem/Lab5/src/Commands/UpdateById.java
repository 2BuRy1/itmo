package Commands;

import Builders.SpaceMarineBuilder;
import Managers.CollectionManager;
import Exceptions.InvalidDataException;
import Exceptions.NoSuchElementException;

/**
 * Команда 'update'
 * Обновляет значение полей элемента коллекции
 */
public class UpdateById extends Command  {
private final CollectionManager collectionManager;
    public UpdateById(CollectionManager collectionManager){
        super("update");

        this.collectionManager=collectionManager;
    }

    /**
     * @param args аргуметы команды
     *  Метод запуска команды
     */
@Override
    public void execute(String args) {
        try{

                int id = Integer.parseInt(args.trim());
                collectionManager.updateByID(id , new SpaceMarineBuilder().create());
                System.out.println("Объект успешно добавлен");

        } catch (NoSuchElementException e) {
            System.err.println("Такого элемента нет в коллекции");;
        } catch (InvalidDataException e) {
            System.err.println("Поля введены некорректно, объект не созддан");;
        }
        catch (NumberFormatException e){
            System.err.println("Введите число типа int");
        }
}
}
