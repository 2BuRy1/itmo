package Commands;

import Managers.CollectionManager;

/**
 * Команда 'help'
 * Выводит сводку по всем имеющимся командам
 */
public class Help extends Command {
    private final CollectionManager collectionManager;
    public Help(CollectionManager collectionManager){
        super("help");
        this.collectionManager = collectionManager;
    }

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */

    @Override
    public void execute(String args) {
        collectionManager.help();
    }
}
