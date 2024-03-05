package Managers;

import Commands.Command;

import java.util.HashMap;

/**
 * Класс, отвечающий за исполнение команд
 */
public class CommandManager {

    private  HashMap<String, Command> commands = new HashMap<>();

    /**
     * @param command команда, добавляемая в Map
     */
    public void addCommand(Command command){

        this.commands.put(command.getName(), command);
    }
    /**
     * @param name название команды
     * @param args ее аргументы(id элемента, index коллекции и тд)
     */
    public void execute(String name, String args){
        Command command = commands.get(name);
        if (command== null){
            System.out.println("Такой команды нет");
        }
        else {
            command.execute(args);
        }
    }
}
