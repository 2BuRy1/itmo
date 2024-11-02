
import managers.RequestHandler;
import managers.ResponseSender;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        RequestHandler requestHandler = new RequestHandler();

        ResponseSender responseSender = new ResponseSender(requestHandler);


        Server server = new Server(responseSender);


        server.run();
    }
}
