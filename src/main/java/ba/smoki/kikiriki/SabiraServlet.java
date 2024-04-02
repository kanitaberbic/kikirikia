package ba.smoki.kikiriki;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sabiraServlet", urlPatterns = "/sabira")
public class SabiraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Prvi Naslov</title></head>");
            out.println("<body>");
            out.println("<h1>");
            String operand1 = request.getParameter("sabirak1");
            Integer number1 = Integer.parseInt(operand1);
            String operand2 = request.getParameter("sabirak2");
            Integer number2 = Integer.parseInt(operand2);
            Integer suma = number1 + number2;
            out.println("SUMA  : " + suma);
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}