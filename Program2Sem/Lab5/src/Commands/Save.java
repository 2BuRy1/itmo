package Commands;

import Managers.CollectionManager;import Managers.FileManager;

import java.io.IOException;

/**
 * Команда 'Save'
 * Позволяет сохранить содержимое коллекции в файл
 */
public class Save extends Command {

private FileManager fileManager;
private final CollectionManager collectionManager;

private final String file_path;

public Save(FileManager fileManager, CollectionManager collectionManager, String file_path){
    super("save");
    this.fileManager=fileManager;
    this.collectionManager=collectionManager;
    this.file_path = file_path;
}


    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
public void execute(String args) {
        try {
            fileManager.saveObjects(file_path);
            System.out.println("Объекты успешно сохранены");
        } catch (IOException e) {
            System.err.println("Файл не найден");
        }
    }
}
