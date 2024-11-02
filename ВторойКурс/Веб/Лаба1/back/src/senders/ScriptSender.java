package senders;

import data.RequestData;
import data.ScriptData;
import interfaces.Sendable;

import java.nio.charset.StandardCharsets;

public class ScriptSender implements Sendable {
    @Override
    public void send(RequestData requestData) {
        ScriptData container = (ScriptData) requestData;

        String content = container.getScript();

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
