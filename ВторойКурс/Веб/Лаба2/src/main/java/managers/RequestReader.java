package managers;

import com.google.gson.Gson;
import data.Dot;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {


    public Dot readRequest(HttpServletRequest request) throws Exception {
        try {
            System.out.println(request.getParameter("x"));
            System.out.println(request.getParameter("y"));
            System.out.println(request.getParameter("r"));


            Integer x = Integer.parseInt(request.getParameter("x"));
            Double y = Double.parseDouble(request.getParameter("y"));
            Integer r = Integer.parseInt(request.getParameter("r"));
            System.out.printf("x : %f, y : %f, r : %d", x, y, r);
            return new Dot(x, y, r);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        return null;
    }
}





