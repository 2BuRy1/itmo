package managers;

import data.*;
import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RequestHandler {



    public RequestData readRequest() throws IOException {
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                FCGIInterface.request.inStream.read(buffer.array(), 0,
                        contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        //DOCUMENT_URI: /api/
        var request = new String(requestBodyRaw, StandardCharsets.UTF_8);

        String uri = FCGIInterface.request.params.getProperty("DOCUMENT_URI");

        Path path = Paths.get("/opt/user/myapp/frontend/index.html");
        switch (uri) {
            case "/index.html", "/" -> {
                String html = Files.readString(path);
                return new HtmlDocument(html);
            }
            case "/dist/canvas.js", "/canvas.js" -> {
                String canvas = Files.readString(Paths.get("/opt/user/myapp/frontend/dist/canvas.js"));
                return new CanvasData(canvas);
            }
            case "/dist/script.js", "/script.js" -> {
                String script = Files.readString(Paths.get("/opt/user/myapp/frontend/dist/script.js"));
                return new ScriptData(script);
            }
            case "/api/" -> {
                return JsonParser.parseJson(request);
            }
        }
        String html = Files.readString(path);
        return new HtmlDocument(html);
    }
}



