package senders;

import data.CanvasData;
import data.RequestData;
import interfaces.Sendable;

import java.nio.charset.StandardCharsets;

public class CanvasSender implements Sendable {
    @Override
    public void send(RequestData requestData) {
        CanvasData container = (CanvasData) requestData;

        String content = container.getCanvas();

        var httpResponse = """
                        HTTP/1.1 200 OK
                        Content-Type: application/javascript
                        Content-Length: %d
                                                
                        %s
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

        // logger.warning("status: %s".formatted(true));
        System.out.println(httpResponse);
    }
}
