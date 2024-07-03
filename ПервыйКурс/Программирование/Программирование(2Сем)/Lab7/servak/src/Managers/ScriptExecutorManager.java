package Managers;

import Interfaces.Reader;

import java.io.*;
import java.util.ArrayDeque;

/**
 Класс для хранения файл менеджера для команды execute
 */
public class ScriptExecutorManager implements Reader {
    private static final ArrayDeque<String> filepaths = new ArrayDeque<>();
    private static final ArrayDeque<BufferedReader> reader = new ArrayDeque<>();

    /**
     * @return Достает первый элемент из очереди и читает его
     * @throws IOException ошибка чтения файла
     */
    public static String readfile() throws IOException {
        return reader.getFirst().readLine();
    }

    /**
     * Добавляет содержимое файла и путь к файлу в очереди
     * @param file путь к файлу
     * @throws FileNotFoundException файл не найден
     */
    public static void pushFile(String file) throws FileNotFoundException {
        reader.push(new BufferedReader(new FileReader(file)));
        filepaths.push(file);
    }

    /**
     * @throws IOException 
     */
    public static void popfile() throws IOException {
        reader.getFirst().close();
        reader.pop();
        filepaths.pop();
    }
    public static boolean fileReapeting(String filepath){
        return filepath.contains(new File(filepath).getAbsolutePath());
    }

    @Override
    public String nextLine() {
        try {
            return readfile();
        } catch (IOException e) {
            return "";
        }
    }
}
