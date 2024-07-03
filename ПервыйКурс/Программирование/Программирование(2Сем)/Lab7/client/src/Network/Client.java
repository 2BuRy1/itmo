package Network;

import java.io.*;
import java.net.*;
import java.util.Objects;

public class Client {

    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;

    private Socket socket;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;


    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;

    }

    public Response sendRequest(Request request) throws InterruptedException {
        this.connect();
        for (int reconnectionAttempts = 0; reconnectionAttempts < maxReconnectionAttempts; reconnectionAttempts = 0) {
            try {
                if (Objects.isNull(serverWriter) || Objects.isNull(serverReader)) throw new IOException();
                if (request.getCommand() == null) System.err.println("Запрос пустой!");
                serverWriter.writeObject(request);
                serverWriter.flush();
                Response response = (Response) serverReader.readObject();
                this.disconnect();
                return response;
            } catch (IOException e) {

                if (reconnectionAttempts >= maxReconnectionAttempts) {
                    break;
                }
                reconnectionAttempts++;

                System.err.println("Повторная попытка через " + reconnectionTimeout / 1000 + " секунд");
                Thread.sleep(reconnectionTimeout);
                this.connect();
            } catch (ClassNotFoundException e) {
                System.err.println("Произошла ошибка при получении данных от сервера");
            }
        }
        return null;
    }


    public void connect() {
        try {
            socket = new Socket(host, port);
            serverWriter = new ObjectOutputStream(socket.getOutputStream());
            serverReader = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Произошла ошибка при соеденении с сервером");
            ;
        }
    }


    public void disconnect() {
        try {

            socket.close();
            serverReader.close();
            serverWriter.close();
        } catch (IOException e) {
            System.err.println("Не подключен к серверу");
            ;
        }
    }

}
