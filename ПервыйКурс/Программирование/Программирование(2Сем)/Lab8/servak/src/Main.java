import Managers.*;
import Commands.*;
import Network.Server;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


public class  Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final Logger serverLogger = getLogger("logger");
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        RunManager runManager = new RunManager(commandManager);
        DataBaseManager dataBaseManager = new DataBaseManager();
        collectionManager.loadCollection();


        commandManager.addCommand(new Info(collectionManager));
        commandManager.addCommand(new Show(collectionManager));
        commandManager.addCommand(new Clear(collectionManager, dataBaseManager));
        commandManager.addCommand(new Sort(collectionManager));
        commandManager.addCommand(new MaxByChapter(collectionManager));
        commandManager.addCommand(new PrintFieldAscendingCategory(collectionManager));
        commandManager.addCommand(new Add(collectionManager, dataBaseManager));
        commandManager.addCommand(new Help(collectionManager));
        commandManager.addCommand(new AddIfMin(collectionManager, dataBaseManager));
        commandManager.addCommand(new Update(collectionManager, dataBaseManager));
        commandManager.addCommand(new Insert(collectionManager));
        commandManager.addCommand(new Remove(collectionManager));
        commandManager.addCommand(new CountLess(collectionManager));
        //commandManager.addCommand(new ExecuteScript(commandManager));
        commandManager.addCommand(new Login(dataBaseManager));
        //commandManager.addCommand(new Register(dataBaseManager));
        Server server = new Server(runManager, 2448, dataBaseManager, collectionManager);


        Thread thread = new Thread(server);
        thread.start();
    }

}

