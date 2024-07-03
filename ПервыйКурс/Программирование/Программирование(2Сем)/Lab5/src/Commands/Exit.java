package Commands;

import Interfaces.Executor;

/**
 * Команда 'exit'
 * Выход из программы без сохранения в файл
 */
public class Exit extends Command {


public Exit(){
    super("exit");
}

    /**
     * @param args аргументы команды
     * Метод запуска команды
     */
@Override
public void execute(String args) {

        System.exit(1);
    }
}
