package Managers;

import Network.Request;
import Network.Response;

/**
 * Класс, обрабатывающий пользовательскй ввод
 */
public class RunManager {
private final CommandManager commandManager;

public RunManager(CommandManager commandManager){
    this.commandManager = commandManager;
    }

    /**
     * Метод, запускающий выполнение команд
     */
    public Response run(Request request, CollectionManager collectionManager) {
        return request.getCommand().execute(request, collectionManager);


    }
}
