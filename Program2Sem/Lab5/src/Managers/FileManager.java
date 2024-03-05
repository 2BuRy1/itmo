package Managers;

import Exceptions.InvalidDataException;
import MainClasses.SpaceMarine;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;

/**
 * Класс, отвечающий за чтение и запись содержимого коллекции в/из файла
 */
public class FileManager {
    private final CollectionManager collectionManager;

    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    private XStream xStream = new XStream();


    /**
     * Метод, позволяющий читать файл и записывать его содержимое в коллекцию
     *
     * @param file_path путь к файлу
     * @throws IOException ошибка чтения файла
     */
    public void readFromCollection(String file_path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            xStream.addPermission(AnyTypePermission.ANY);
            xStream.alias("marines", CollectionManager.class);
            xStream.alias("MainClasses.SpaceMarine", SpaceMarine.class);
            xStream.addPermission(AnyTypePermission.ANY);
            CollectionManager commandsWithObjects = (CollectionManager) xStream.fromXML(stringBuilder.toString());
            for (int i = 0; i < commandsWithObjects.getMarines().size() - 1; i++) {
                if (!commandsWithObjects.CheckSameId() ) {
                    System.err.println("У объектов повторяются id");
                    System.exit(1);
                }
            }
            collectionManager.addElements(commandsWithObjects.getMarines());
            collectionManager.setLocalDate(commandsWithObjects.getlocalDate());
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");

        } catch (InvalidDataException e ) {
            System.err.println("Объекты не валидны, не удалось загрузить коллекцию");

        }
    }

        /**
         * Метод, позволяющий записывать содержимое коллекции в файл
         * @param file_path путь к файлу
         * @throws IOException ошибка чтения файла
         */
        public void saveObjects(String file_path) throws IOException {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file_path));
            printWriter.write(xStream.toXML(this.collectionManager));
            printWriter.close();
        }
    }


