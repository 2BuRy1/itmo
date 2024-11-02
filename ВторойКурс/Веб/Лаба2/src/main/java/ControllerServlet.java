

import data.Dot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


public class ControllerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("x") != null && request.getParameter("y") != null && request.getParameter("r") != null) {
            request.getRequestDispatcher("/check").forward(request, response);
        }
        else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.getWriter().write("Only Get Method allowed");
    }



}
