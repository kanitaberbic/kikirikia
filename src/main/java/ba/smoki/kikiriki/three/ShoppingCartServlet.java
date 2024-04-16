package ba.smoki.kikiriki.three;

import ba.smoki.kikiriki.two.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ba.smoki.kikiriki.two.WebShopServlet.PRODUCTS;

@WebServlet(urlPatterns = {"/cart"})
public class ShoppingCartServlet extends HttpServlet {

    public static final String KORPA = "KORPA";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
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
            out.println("<head><title>Shopping cart</title></head>");
            out.println("<body>");
            HttpSession session = request.getSession();
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KORPA);
            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                session.setAttribute(KORPA, shoppingCart);
            }
            Long productId = Long.parseLong(request.getParameter("productId"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
            Product product = products
                    .stream()
                    .filter(p -> p.getId().equals(productId))
                    .findFirst()
                    .orElseThrow();
            shoppingCart.addCartItem(product, quantity);
//            for(Product product: products){
//                if(product.getId().equals(productId)){
//                    shoppingCart.addCartItem(product, quantity);
//                    break;
//                }
//            }
            if (!shoppingCart.getShoppingCartItems().isEmpty()) {
                out.println("<h1>Artikli u korpi</h1>");
                out.println("<table>");
                out.println("<tr bgcolor='lightgray'>" +
                            "<th>Naziv</th>" +
                            "<th>Jedinična cijena</th>" +
                            "<th>Quantity</th>" +
                            "<th>Ukupna cijena</th>" +
                            "</tr>");
                for (ShoppingCartItem item : shoppingCart.getShoppingCartItems()) {
                    out.println("<tr>");
                    out.println("<td>" + item.getProduct().getName() + "</td>");
                    out.println("<td>" + item.getProduct().getPrice() + "</td>");
                    out.println("<td>" + item.getQuantity() + "</td>");
                    out.println("<td>" + item.getTotalPrice() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<h1>Nema proizvoda u korpi</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
