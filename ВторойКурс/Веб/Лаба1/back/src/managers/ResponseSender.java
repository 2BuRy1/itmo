package managers;

import data.CanvasData;
import data.Dot;
import data.HtmlDocument;
import data.ScriptData;
import interfaces.Sendable;
import senders.CanvasSender;
import senders.HtmlSender;
import senders.JsonSender;
import senders.ScriptSender;
import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.util.logging.Logger;

public class ResponseSender {


    final Logger logger = LoggerConfig.getLogger(this.getClass().getName());

    private final RequestHandler requestHandler;

    Sendable sender;


    public ResponseSender( RequestHandler requestHandler) {
        this.requestHandler = requestHandler;

    }


    public void sendResponse() throws IOException {


        var fcgiInterface = new FCGIInterface();

        logger.info("Waiting for requests...");
        while (fcgiInterface.FCGIaccept() >= 0) {
            var request = requestHandler.readRequest();
            sender = request instanceof Dot ? new JsonSender() :
                    request instanceof CanvasData ? new CanvasSender() :
                            request instanceof HtmlDocument ? new HtmlSender() :
                                    request instanceof ScriptData ? new ScriptSender() :
                                            new CanvasSender();

            if(request == null) {
                sender = new JsonSender();
            }

            sender.send(request);
        }
    }

}