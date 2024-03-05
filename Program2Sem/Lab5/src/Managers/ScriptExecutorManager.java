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
     * @return Читает содержимое файла
     * @throws IOException ошибка чтения файла
     */
    public static String readfile() throws IOException {
        return reader.getFirst().readLine();
    }

    /**
     * Добавляет файл в очереди для дальнейшего взаимодействия с ним
     * @param file путь к файлу
     * @throws FileNotFoundException файл не найден
     */
    public static void pushFile(String file) throws FileNotFoundException {
        reader.push(new BufferedReader(new FileReader(file)));
        filepaths.push(file);
    }

    /**
     * Удаляет файл из очередей
     * @throws IOException Ошибка ввода-ввывода
     */
    public static void popfile() throws IOException {
        reader.getFirst().close();
        reader.pop();
        filepaths.pop();
    }

    /**
     * @param filepath путь к файлу
     * @return Проверяет наличие рекурсии при выполнении программы
     */
    public static boolean fileReapeting(String filepath){
        return filepath.contains(new File(filepath).getAbsolutePath());
    }

    /**
     * @return Взаимодействие вводом полей для команд, создающих новый объект
     */
    @Override
    public String nextLine() {
        try {
            return readfile();
        } catch (IOException e) {
            return "";
        }
    }
}
