import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.FileManager;
import Commands.*;
import Managers.RunManager;
import Network.Server;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        final Logger serverLogger = Logger.getLogger("logger");
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(collectionManager);
        RunManager runManager = new RunManager(commandManager);
        if (args.length != 0) {
            try {
                fileManager.readFromCollection(args[0]);
            } catch (IOException e) {
                serverLogger.info("Ошибка чтения файла");;
            }
            serverLogger.info("Файл успешно загружен");
        } else {
            serverLogger.info("Файл не обнаружен");
            System.exit(1);
        }


        commandManager.addCommand(new Info(collectionManager));
        commandManager.addCommand(new Show(collectionManager));
        commandManager.addCommand(new Clear(collectionManager));
        commandManager.addCommand(new Sort(collectionManager));
        commandManager.addCommand(new MaxByChapter(collectionManager));
        commandManager.addCommand(new PrintFieldAscendingCategory(collectionManager));
        commandManager.addCommand(new Add(collectionManager));
        commandManager.addCommand(new Help(collectionManager));
        commandManager.addCommand(new AddIfMin(collectionManager));
        commandManager.addCommand(new Update(collectionManager));
        commandManager.addCommand(new Insert(collectionManager));
        commandManager.addCommand(new Remove(collectionManager));
        commandManager.addCommand(new CountLess(collectionManager));
        commandManager.addCommand(new ExecuteScript(commandManager));
        Server server = new Server(runManager, 2448, fileManager);
        server.run(args[0]);

    }

}

