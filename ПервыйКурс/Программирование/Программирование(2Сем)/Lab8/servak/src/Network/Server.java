package Network;

import Managers.CollectionManager;
import Managers.DataBaseManager;
import Managers.RunManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class Server implements Runnable{
    private ServerSocketChannel ss;
    private int port;
    private Response response;
    private  Request request;

    private static final Logger serverLogger = Logger.getLogger("logger");

    private DataBaseManager dataBaseManager;

    private RunManager runtimeManager;

    private final CollectionManager collectionManager;

    private BufferedInputStream bf = new BufferedInputStream(System.in);
    private BufferedReader scanner = new BufferedReader(new InputStreamReader(bf));

    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private ForkJoinPool forkJoinPool = new ForkJoinPool(3);

    public Server(RunManager runtimeManager, int port, DataBaseManager dataBaseManager, CollectionManager collectionManager) {
        this.runtimeManager = runtimeManager;
        this.port = port;
        this.dataBaseManager = dataBaseManager;
        this.collectionManager = collectionManager;
    }


    @Override
    public void run()  {
        openServerSocket();
        serverLogger.info("Создано соединение с клиентом");
            try {
                while (true) {
                    if (scanner.ready()) {
                        String line = scanner.readLine();
                        if (line.equals("save") || line.equals("s")) {
                            serverLogger.info("Коллекция успешно сохранена!");
                            collectionManager.loadCollection();
                        }
                        if (line.equals("exit")) {
                            collectionManager.loadCollection();
                            serverLogger.info("Коллекция успешно сохранена, всего доброго!");
                            System.exit(0);
                        }
                    }
                    SocketChannel clientSocket = ss.accept();
                    if (clientSocket != null) {
                        processClientRequest(clientSocket);

                }

        }
        } catch (IOException | ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

    private void processClientRequest(SocketChannel clientSocket) throws ExecutionException, InterruptedException {

        try(ObjectInputStream reader = new ObjectInputStream(clientSocket.socket().getInputStream()); ObjectOutputStream writer= new ObjectOutputStream(clientSocket.socket().getOutputStream())) {


            cachedThreadPool.submit(() -> readRequest(reader)).get();
             serverLogger.info("Получено сообщение от клиента " + request.getUser().getLogin() + ": " + request.getCommand().getName());
           forkJoinPool.submit(() -> commandExecution(request)).get();
            cachedThreadPool.submit(() -> sendResponse(response, writer)).get();
               serverLogger.info("Отправлено сообщение клиенту " + request.getUser().getLogin() + ": " + response.getResult());

            } catch (IOException | InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    private synchronized void readRequest(ObjectInputStream objectInputStream) {
        try {
            request = (Request) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    private  Response  commandExecution(Request request) {
        response =  request.getCommand().execute(request, collectionManager);
        return response;
    }

    private synchronized void sendResponse(Response s,  ObjectOutputStream writer) {
            try {
                writer.writeObject(s);
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private void openServerSocket() {
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(port));
            ss.configureBlocking(false);
        } catch (IOException e) {
              serverLogger.warning("Произошла ошибка при попытке использовать порт");
        }
    }
}