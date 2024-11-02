import com.google.gson.Gson;
import data.Dot;
import managers.FunctionCalc;
import managers.RequestReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    RequestReader requestReader = new RequestReader();
    Gson gson = new Gson();

    FunctionCalc functionCalc = new FunctionCalc();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Dot dot = new Dot(Double.parseDouble(request.getParameter("x")), Double.parseDouble(request.getParameter("y")), Double.parseDouble(request.getParameter("r")));
            dot.status(functionCalc.isInTheSpot(dot));


            List<Dot> dots = (List<Dot>) request.getSession().getAttribute("result");
            if (dots == null) {

                dots = new ArrayList<>();

            }
             dots.add(0, dot);
            request.getSession().setAttribute("result", dots);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            for (Dot dotss : dots) {
                System.out.println(dotss.getX());
            }
            String answer = String.format(gson.toJson(dot));
            response.getWriter().write(answer);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String answer = "{error: %s }".formatted(e.getMessage());
            response.getWriter().write(answer);
        }
    }
}




