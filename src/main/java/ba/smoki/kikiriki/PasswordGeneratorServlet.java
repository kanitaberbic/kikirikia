package ba.smoki.kikiriki;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class PasswordGeneratorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter();){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Prvi Naslov</title></head>");
            out.println("<body>");
            out.println("<h1>");
            out.println("Generisana lozinka: " + randomPassword());
            out.println("</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     *  <li>min: 5</li>
     *  <li>max: 15</li>
     *  <li>uvijek je različita dužina</li>
     *
     * @return password
     */
    private String randomPassword(){
        String moguciKarakteri = "ABCDEFGHabcdefghijklmnopqrstuvxyz1234567*?!#";
        Random random = new Random();
        int duzinaLozinka = 5+random.nextInt(11);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<duzinaLozinka; i++){
            int slucajnaPozicija = random.nextInt(moguciKarakteri.length());
            char karakterNaSlucajnojPoziciji = moguciKarakteri.charAt(slucajnaPozicija);
            stringBuilder.append(karakterNaSlucajnojPoziciji);
        }
        return stringBuilder.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
