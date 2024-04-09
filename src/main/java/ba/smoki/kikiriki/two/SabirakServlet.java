package ba.smoki.kikiriki.two;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/sabirak_servlet"})
public class SabirakServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Sabirak SERVLET</title></head>");
            String param1 = request.getParameter("sabirak1");
            Integer num1 = Integer.parseInt(param1);
            String param2 = request.getParameter("sabirak2");
            Integer num2 = Integer.parseInt(param2);
            out.println("<body>");
            out.println("<h1>Sabirak</h1>");
            out.println("<p> Suma : " + (num1 + num2) + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
