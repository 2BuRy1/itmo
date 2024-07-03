package Network;

import Builders.SpaceMarineBuilder;
import Commands.*;
import Exceptions.InvalidDataException;
import MainClasses.ListOfCommands;
import MainClasses.SpaceMarine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Program {
    public void execute() throws InvalidDataException, InterruptedException {
        ListOfCommands listOfCommands = new ListOfCommands();
        listOfCommands.putCommands(new Update());
        listOfCommands.putCommands(new Show());
        listOfCommands.putCommands(new Clear());
        listOfCommands.putCommands(new Sort());
        listOfCommands.putCommands(new MaxByChapter());
        listOfCommands.putCommands(new PrintFieldAscendingCategory());
        listOfCommands.putCommands(new Add());
        listOfCommands.putCommands(new Help());
        listOfCommands.putCommands(new AddIfMin());
        listOfCommands.putCommands(new Info());
        listOfCommands.putCommands(new Insert());
        listOfCommands.putCommands(new Remove());
        listOfCommands.putCommands(new CountLess());
        listOfCommands.putCommands(new ExecuteScript());
        String[] input;
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("localhost", 2448, 5000, 5);

        System.out.println("Введите help для вывода справки по командам: ");
        while (true) {
            String cmd = (scanner.nextLine() + " ").trim();
            input = cmd.split(" ");
            if (input[0].equals("exit")) {
                System.out.println("До свидания!");
                System.exit(1);
            }
            if (listOfCommands.getCollection().get(input[0]) == null) {
                System.err.println("Такой команды нет");
                        continue;
            }
                Command command = listOfCommands.getCollection().get(input[0]);
                if (!command.isHasArguments()) {
                    if (input.length != 1) {
                        System.err.println("Этой команде не нужны аргументы");
                        continue;
                    }
                    if (input[0].equals("add") || input[0].equals("add_if_min")) {
                        SpaceMarine spaceMarine = new SpaceMarineBuilder().create();
                        System.out.println(client.sendRequest(new Request(command, spaceMarine)).getResult());

//                        Request request = new Request(command);
//                        System.out.println(client.sendRequest(request).getResult());
                    }
                    Request request = new Request(command);
                    System.out.println(client.sendRequest(request).getResult());
                    continue;
                }
                    if (input.length != 2) {
                        System.err.println("Этой команде нужен ровно один аргумент");
                        continue;
                    }
                    if (!input[0].equals("execute_script") && !input[0].equals("count_less_than_melee_weapon")) {
                        int id;
                        id = Integer.parseInt(input[1]);
                        if (input[0].equals("insert_at") || input[0].equals("update")) {
                           SpaceMarine spaceMarine = new SpaceMarineBuilder().create();
                            System.out.println(client.sendRequest(new Request(command, spaceMarine, id)).getResult());
                            continue;
                        }
                       if(input[0].equals("remove_by_id")) {

                           System.out.println(client.sendRequest(new Request(command,  id)).getResult());
                           continue;
                       }

                        Request request = new Request(command, input[1].toUpperCase());
                        System.out.println(client.sendRequest(request).getResult());
//
//
                    }



            }
        }

    }

