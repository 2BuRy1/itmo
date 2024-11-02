package senders;

import data.Dot;
import data.RequestData;
import interfaces.Sendable;
import managers.FunctionCalc;

import java.nio.charset.StandardCharsets;

public class JsonSender implements Sendable {

    FunctionCalc functionCalc = new FunctionCalc();
    @Override
    public void send(RequestData requestData) {
        var start = System.currentTimeMillis();
        Dot dot = (Dot) requestData;
                    try {
                var status = functionCalc.isInTheSpot(dot);
                var content = """
                        {

                        "status": %s,
                        "time": %s,
                        "x": %d,
                        "y": %f,
                        "r": %d

                        }
                        """;
                var end = System.currentTimeMillis();
                content = content.formatted(status, String.format("%.4f", (double) (end - start) / 1000), dot.getX(), dot.getY(), dot.getR());
                var httpResponse = """
                        HTTP/1.1 200 OK
                        Content-Type: application/json
                        Content-Length: %d

                        %s
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                //logger.warning("status: %s".formatted(true));
                System.out.println(httpResponse);


            } catch (Exception e) {
                var content = """
                        {
                        "error": "%s"
                        }
                        """;
                content = content.formatted(e.getMessage());
                var httpResponse = """
                        HTTP/1.1 400 Bad Request
                        Status: 400
                        Content-Type: application/json
                        Content-Length: %d

                        %s
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);

                System.out.println(httpResponse);


            }


        }



    }

