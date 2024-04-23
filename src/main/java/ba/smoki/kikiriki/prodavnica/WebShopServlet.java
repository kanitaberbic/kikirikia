package ba.smoki.kikiriki.prodavnica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ba.smoki.kikiriki.korisnici.UserServlet.KORISNICKO_IME;

@WebServlet(name = "webShopServlet", urlPatterns = "/prodavnica")
public class WebShopServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(WebServlet.class.getName());
    public static final String PRODUCTS = "products";

    @Override
    public void init() throws ServletException {
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            List<Product> products = new ArrayList<>();
            while((line = br.readLine())!=null){
                String[] parts = line.split(";");
                Long id = Long.parseLong(parts[0]);
                String name = parts[1];
                BigDecimal price = new BigDecimal(parts[2]);
                price.setScale(2, RoundingMode.HALF_UP);
                Product product = new Product(id, name, price);
                products.add(product);
            }
            getServletContext().setAttribute(PRODUCTS, products);
        }catch (IOException ex){
            LOGGER.info(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String korisnickoImeForma = request.getParameter(KORISNICKO_IME);
        if(korisnickoImeForma != null) {
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


                out.println("<h1>Ponuda</h1>");
                out.println("<table>");
                out.println("<tr bgcolor='maroon'><th>Naziv</th><th>Cijena</th><th>Količina</th></tr>");
                List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
                out.println("<form method='post' action='/kikiriki/korpa'>");
                for (Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getName() + "</td>");
                    out.println("<td>" + product.getPrice() + "</td>");
                    out.println("<td>");
                    out.println("<input type='number' name='quantity' value='0' min='-100' max='100' step='1'/>");
                    out.println("<input type='hidden' name='productId' value='" + product.getId() + "'/>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<button class='btn-login'>Dodaj u korpu</button>");
                out.println("</form>");
                out.println("<form method='post' action='/kikiriki/odjava'>");
                out.println("<button class='btn-new'>Napusti prodavnicu</button>");
                out.println("</form>");

                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</main>");


                out.println("</body>");
                out.println("</html>");
            }
        }else {
            response.sendRedirect("/kikiriki/pogresna_prijava.html");
        }
    }
}
