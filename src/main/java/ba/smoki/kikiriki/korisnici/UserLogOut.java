package ba.smoki.kikiriki.korisnici;


import ba.smoki.kikiriki.korpa.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import static ba.smoki.kikiriki.korisnici.UserServlet.KORISNICKO_IME;
import static ba.smoki.kikiriki.korpa.ShoppingCartServlet.KORPA;

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

        HttpSession session = request.getSession();
        String korisnickoImeSesija = session.getAttribute(KORISNICKO_IME).toString();

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
            out.println("<hr/><h1>Korisnik " + korisnickoImeSesija + " je odjavljen</h1><br/>Korpa je ispražnjena<br/><hr/>");
            out.println("<button class='btn-login'>Početna strana</button>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");

            session.setAttribute(KORISNICKO_IME, null);
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KORPA);
            if (!shoppingCart.getShoppingCartItems().isEmpty()) {
                session.removeAttribute(KORPA);
            }
        }
    }
}


