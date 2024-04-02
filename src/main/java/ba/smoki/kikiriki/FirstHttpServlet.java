package ba.smoki.kikiriki;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet je ako i samo ako :
 * <li>1. extends {@link jakarta.servlet.http.HttpServlet}</li>
 * <li>2. mora se izložiti na nekoj adresi
 *      http://localhost:8080/kikiriki-1.0-SNAPSHOT/first
 * </li>
 */
public class FirstHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter();){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Prvi Naslov</title></head>");
            out.println("<body><h1>Pauza č šć</h1><p>Lorem ipsum pauza</p></body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
