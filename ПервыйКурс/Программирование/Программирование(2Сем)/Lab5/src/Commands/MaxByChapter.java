package Commands;

import Managers.CollectionManager;

import Exceptions.EmptyCollectionException;

/**
 * Команда 'max_by_chapter'
 * Выводит элемент, значение поля chapter которого является максимальным
 */
public class MaxByChapter extends Command{

    private final CollectionManager collectionManager;

    public MaxByChapter(CollectionManager collectionManager){
        super("max_by_chapter");
        this.collectionManager=collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
    @Override
    public  void execute(String args){

        try{
            System.out.println("Элемент, значения поля chapter которого является максимальным: ");
            collectionManager.max_by_chapter();
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста");;
        }
    }
}
