package MainClasses;

import Commands.Command;

import java.util.HashMap;

public class ListOfCommands {

    private HashMap<String, Command> commands = new HashMap<>();


    public void putCommands(Command command) {
        commands.put(command.getName(), command);
    }

    public HashMap<String, Command> getCollection() {
        return commands;
    }


}

