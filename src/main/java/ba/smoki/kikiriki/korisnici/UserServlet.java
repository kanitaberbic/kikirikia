package ba.smoki.kikiriki.korisnici;

import ba.smoki.kikiriki.korpa.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

@WebServlet(name="userServlet", urlPatterns = "/korisnici")
public class UserServlet extends HttpServlet {

    public static final String KORISNICKO_IME = "KORISNIK";
    public static final String KORPA = "KORPA";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);

    }

    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String korisnickoIme = request.getParameter("korisnicko_ime");
        String lozinka = request.getParameter("lozinka");
        String kreditnaKartica = request.getParameter("kreditna_kartica");
        String path = getServletContext().getRealPath("/WEB-INF/korisnici.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(korisnickoIme + ";" + lozinka + ";" + kreditnaKartica);
            writer.newLine();
            getServletContext().setAttribute(KORISNICKO_IME, korisnickoIme);

            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Hvala na kupovini</title></head>");
            out.println("<link rel='stylesheet' type='text/css' href='prodavnica.css' media='screen'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<main>");
            out.println("<div class='row'>");
            out.println("<div class='colm-form'>");
            out.println("<div class='form-container'>");

            out.println("<h1>Transakcija je bila uspješna. </h1>");
            out.println("<table>");
            out.println("<tr bgcolor='maroon'><th>Hvala na povjerenju.</th></tr>");
            String korisnik = (String) getServletContext().getAttribute(KORISNICKO_IME);
            out.println("<form method='get' action='/kikiriki/prodavnica'>");

            out.println("<tr>");
            out.println("<td><h2>"+ korisnik +"</h2></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<input type='hidden' value="+korisnik+" name='korisnicko_ime'/>");

            HttpSession session = request.getSession();
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KORPA);
            if (!shoppingCart.getShoppingCartItems().isEmpty()) {
                session.removeAttribute(KORPA);
            }

            out.println("<button class='btn-login'>Nastavi kupovinu</button>");
            out.println("</form>");

            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");

            out.println("</body>");
            out.println("</html>");
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        }
    }




