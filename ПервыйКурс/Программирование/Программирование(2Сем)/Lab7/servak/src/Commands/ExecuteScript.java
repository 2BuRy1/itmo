package Commands;

import Builders.SpaceMarineBuilder;
import MainClasses.SpaceMarine;
import Managers.CommandManager;
import Managers.FileMode;
import Managers.ScriptExecutorManager;
import Network.Request;
import Network.Response;

import java.io.*;

public class ExecuteScript extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 20L;

    private final CommandManager commandManager;
    public ExecuteScript(CommandManager commandManager) {
        super("execute_script");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request request) {
        String path = (String) (request.getArgs());
        path = path.trim();
        StringBuilder stringBuilder = new StringBuilder();
            try {
                FileMode.setFileMode(true);
                ScriptExecutorManager.pushFile(path);

                for (String line = ScriptExecutorManager.readfile(); line != null; line = ScriptExecutorManager.readfile()) {
                    String[] cmd = (line.trim() + " ").split(" ", 2);
                    if (cmd[0].equals("execute_script")) {
                        if (ScriptExecutorManager.fileReapeting(cmd[1])) {
                            stringBuilder.append(("Обнаружена рекурсия по пути %s".formatted(new File(cmd[1]).getAbsolutePath()))).append("\n\n");
                            continue;
                        }
                    }
                    if (commandManager.getCommands().get(cmd[0]) != null) {
                        if(cmd[0].equals("remove_by_id")){
                            stringBuilder.append(commandManager.execute(new Request(commandManager.getCommands().get(cmd[0]),Integer.parseInt(cmd[1].trim()), request.getUser())).getResult()).append("\n\n");
                        }
                        if(cmd[0].equals("add") || cmd[0].equals("add_if_min")){
                            SpaceMarine spaceMarine = new SpaceMarineBuilder().create();
                            stringBuilder.append(commandManager.execute(new Request(commandManager.getCommands().get(cmd[0]), spaceMarine, request.getUser())).getResult()).append("\n\n");
                        }
                        if(cmd[0].equals("insert_at")  || cmd[0].equals("update")){
                            SpaceMarine spaceMarine = new SpaceMarineBuilder().create();
                            stringBuilder.append(commandManager.execute(new Request(commandManager.getCommands().get(cmd[0]), spaceMarine, Integer.parseInt(cmd[1].trim()), request.getUser())).getResult()).append("\n\n");

                        }
                        else {
                            stringBuilder.append(commandManager.execute(new Request(commandManager.getCommands().get(cmd[0]),cmd[1], request.getUser())).getResult()).append("\n\n");
                        }
                    }
                    if(cmd[0].equals("execute_script")){
                        ScriptExecutorManager.popfile();
                    }


                }
                FileMode.setFileMode(false);
                return new Response(stringBuilder.toString());

            } catch (FileNotFoundException e) {
                return new Response("Файл не найден");
            } catch (IOException e) {
                return new Response("Ошибка чтения");
            }


    }
}
