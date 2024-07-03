package Commands;
import Managers.CommandManager;
import Managers.FileMode;
import Managers.ScriptExecutorManager;
import java.io.*;
import java.util.NoSuchElementException;


/**
 * Команда 'execute_script'
 * Запускает скрипт, в котором содержатся команды для взаимодействия с коллекцией
 */
public class ExecuteScript extends Command {

    private final CommandManager commandManager;



    public ExecuteScript( CommandManager commandManager) {
        super("execute_script");
        this.commandManager = commandManager;
    }

    /**
     * @param args аргументы команжы
     * Метод запуска команды
     */
    @Override
    public void execute(String args) {
        String path = args.trim();
        try {
            FileMode.setFileMode(true);
            ScriptExecutorManager.pushFile(path);
            for (String line = ScriptExecutorManager.readfile(); line != null; line = ScriptExecutorManager.readfile()) {
                try{
                    String[] cmd = (line + " ").split(" ", 2);
                    if (cmd[0].isBlank()) return;
                    if (cmd[0].equals("execute_script")) {
                        if (ScriptExecutorManager.fileReapeting(cmd[1])){
                            System.err.println("Найдена рекурсия по пути " + new File(cmd[1]).getAbsolutePath());
                            continue;
                        }

                    }
                    System.out.println("Выполнение команды " + cmd[0]);
                    commandManager.execute(cmd[0], cmd[1]);
                    if (cmd[0].equals("execute_script")){
                        ScriptExecutorManager.popfile();
                    }
                } catch (NoSuchElementException ignored) {
                }
            }
            ScriptExecutorManager.popfile();
        }
        catch (FileNotFoundException fileNotFoundException){
            System.err.println("Такого файла не существует");
        } catch (IOException e) {
            System.err.println("Ошибка ввода вывода");
        }catch (NoSuchElementException ignored) {
        }
        FileMode.setFileMode(false);
    }
}
