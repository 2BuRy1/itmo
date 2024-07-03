package Managers;

/**
 * Класс, отвечающий за смену пользовательского ввода на ввод из файла
 */
public class FileMode {


    public static boolean isFileMode = false;

    /**
     * Метод, отвечающий за смену способа ввода команд
     * @param FileMode способ исполнения команды(в интерактивном режиме имеет значение false)
     */
    public static void setFileMode(boolean FileMode) {
        Managers.FileMode.isFileMode = FileMode;
    }
}

