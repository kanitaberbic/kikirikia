package ba.smoki.kikiriki.two;

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

@WebServlet(name = "webShopServlet", urlPatterns = {"/webshop"})
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Produkti u ponudi</title></head>");
            out.println("<body>");
            out.println("<h1>Web shop artikli</h1>");
            out.println("<table border='1'>");
            out.println("<tr bgcolor='lightgray'><th>Naziv</th><th>Cijena</th><th>Dodaj</th></tr>");
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);

            for(Product product: products){
                out.println("<tr>");
                out.println("<td>"+product.getName()+"</td>");
                out.println("<td>"+product.getPrice()+"</td>");
                out.println("<td>");
                out.println("<form method='get' action='/kikiriki-1.0-SNAPSHOT/cart'>");
                out.println("<input type='number' name='quantity'/>");
                out.println("<input type='hidden' name='productId' value='"+product.getId()+"'/>");
                out.println("<input type='submit' value='Dodaj'/>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
