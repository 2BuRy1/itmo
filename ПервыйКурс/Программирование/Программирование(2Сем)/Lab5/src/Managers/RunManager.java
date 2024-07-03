package Managers;

import java.util.Scanner;

/**
 * Класс, обрабатывающий пользовательскй ввод
 */
public class RunManager {
private final CommandManager commandManager;

public RunManager(CommandManager commandManager){
    this.commandManager = commandManager;
    }

    /**
     * Метод, запускающий выполнение команд
     */
    public void run() {
        Scanner scanner = UserScanner.getUserScanner();
        while (true) {
            String CommandToSplit = scanner.nextLine().trim() + " ";
            String[] command = CommandToSplit.split(" ", 2);
            commandManager.execute(command[0], command[1]);
        }

    }
}
