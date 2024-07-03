import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.FileManager;
import Commands.*;
import Managers.RunManager;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager = new FileManager(collectionManager);
        RunManager runManager = new RunManager(commandManager);
        if (args.length != 0) {
            try {
                fileManager.readFromCollection(args[0]);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");;
            }
            System.out.println("Коллекция успешно загружена!");
        } else {
            System.out.println("Файл не обнаружен");
            System.exit(1);
        }

        System.out.println("Для сводки по командам введите : help");
        commandManager.addCommand(new Add(collectionManager));
        commandManager.addCommand(new Help(collectionManager));
        commandManager.addCommand(new AddIfMin(collectionManager));
        commandManager.addCommand(new Clear(collectionManager));
        commandManager.addCommand(new CountByMeleeWeapon(collectionManager));
        commandManager.addCommand(new Info(collectionManager));
        commandManager.addCommand(new InsertAtIndex(collectionManager));
        commandManager.addCommand(new MaxByChapter(collectionManager));
        commandManager.addCommand(new PrintFieldAscendingCategory(collectionManager));
        commandManager.addCommand(new RemoveById(collectionManager));
        commandManager.addCommand(new Show(collectionManager));
        commandManager.addCommand(new Sort(collectionManager));
        commandManager.addCommand(new UpdateById(collectionManager));
        commandManager.addCommand(new Exit());
        commandManager.addCommand(new Save(fileManager, collectionManager, args[0]));
        commandManager.addCommand(new ExecuteScript( commandManager));


        runManager.run();
    }

}

