package Network;

import Exceptions.ServerException;
import Managers.FileManager;
import Managers.RunManager;

import java.io.*;
import java.net.*;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.Logger;

public class Server {

    ServerSocketChannel ss;

    private int port;

    private static final Logger serverLogger = Logger.getLogger("logger");

    FileManager fileManager;
    public RunManager runtimeManager;

    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader scanner = new BufferedReader(new InputStreamReader(bf));

    public Server(RunManager runtimeManager, int port, FileManager fileManager) {
        this.runtimeManager = runtimeManager;
        this.port = port;
        this.fileManager = fileManager;
    }

    public void run(String path) {
        try {
            openServerSocket();
            serverLogger.info("Создано соединение с клиентом");

            while (true) {
                if (scanner.ready()) {
                    String line = scanner.readLine();
                    if (line.equals("save") || line.equals("s")) {
                        fileManager.saveObjects(path);
                        serverLogger.info("Обекты успешно сохранены");
                    }
                    if (line.equals("exit")) {
                        fileManager.saveObjects(path);
                        serverLogger.info("Обекты успешно сохранены");
                        System.exit(0);
                    }
                }

                SocketChannel clientSocket = ss.accept();
                if (clientSocket != null) {
                    processClientRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            serverLogger.warning("Произошла ошибка при работе сервера");
        }
    }

    private void openServerSocket() {
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(port));
            ss.configureBlocking(false);
        } catch (IOException exception) {
            serverLogger.warning("Произошла ошибка при попытке использовать порт");
        }
    }

    private void processClientRequest(SocketChannel clientSocket) {
        Request userRequest;
        Response responseToUser;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.socket().getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())) {

            userRequest = (Request) clientReader.readObject();
            serverLogger.info("Получен запрос с командой " + userRequest.getCommand().getName());
            responseToUser = runtimeManager.run(userRequest);
            clientWriter.writeObject(responseToUser);
            serverLogger.info("Отправлен ответ " + responseToUser.getResult());
            clientWriter.flush();
        } catch (ClassNotFoundException | InvalidClassException | NotSerializableException exception) {
            serverLogger.warning("Произошла ошибка при взаимодействии с клиентом!");
        } catch (IOException exception) {
            serverLogger.warning("Ошибка ввода вывода");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                serverLogger.warning("Ошибка при закрытии клиентского сокета");
            }
        }
    }
}
