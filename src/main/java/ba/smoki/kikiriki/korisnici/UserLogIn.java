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
import java.util.StringTokenizer;

@WebServlet("/prijava")
public class UserLogIn extends HttpServlet {

    public static final String KORISNICKO_IME = "KORISNIK";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = getServletContext().getRealPath("/WEB-INF/korisnici.txt");
        BufferedReader citac = new BufferedReader(new FileReader(path));
        String line;
        String korisnickoImeForma = request.getParameter("korisnicko_ime");
        String lozinkaForma = request.getParameter("lozinka");

        boolean uspjesnaPrijava = false;

        while ((line = citac.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ";");

            if (tokenizer.countTokens() == 3) {
                String korisnickoIme = tokenizer.nextToken();
                String lozinka = tokenizer.nextToken();
                String kreditnaKartica = tokenizer.nextToken();

                if (korisnickoImeForma.equals(korisnickoIme) && lozinkaForma.equals(lozinka)) {
                    uspjesnaPrijava = true;
                    break;
                }
            }


        }
        citac.close();

        HttpSession session = request.getSession();
        if (uspjesnaPrijava) {
            session.setAttribute(KORISNICKO_IME, korisnickoImeForma);
            response.sendRedirect("/kikiriki/prodavnica");
        } else {
            session.setAttribute(KORISNICKO_IME, null);
            response.sendRedirect("pogresna_prijava.html");
        }
    }

}
