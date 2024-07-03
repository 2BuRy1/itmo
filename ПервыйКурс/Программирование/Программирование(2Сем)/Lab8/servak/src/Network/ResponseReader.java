package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class ResponseReader implements Runnable{

    private ObjectInputStream objectInputStream;

    private SocketChannel  socketChannel;

    public  ResponseReader(ObjectInputStream objectInputStream, SocketChannel socketChannel){
        this.objectInputStream = objectInputStream;
        this.socketChannel = socketChannel;


    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
            Response response = (Response) objectInputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
