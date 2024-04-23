package ba.smoki.kikiriki.korisnici;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

@WebServlet("/odjava")
public class UserLogOut extends HttpServlet {


        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String korisnickoImeForma = request.getParameter("KORISNIK");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Kikiriki prodavnica</title></head>");
            out.println("<link rel='stylesheet' type='text/css' href='prodavnica.css' media='screen'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<main>");
            out.println("<div class='row'>");
            out.println("<div class='colm-form'>");
            out.println("<div class='form-container'>");
            out.println("<form action='/kikiriki/' method='post'>");
            out.println("<hr/><h1>Korisnik" + korisnickoImeForma + " je odjavljen</h1><hr/>");
            out.println("<button class='btn-login'>Početna strana</button>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");


            HttpSession session = request.getSession();
            session.setAttribute("korisnickoIme", null);
            response.sendRedirect("/kikiriki/pocetna.html");
        }
    }
}


