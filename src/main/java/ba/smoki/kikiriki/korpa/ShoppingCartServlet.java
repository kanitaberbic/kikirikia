package ba.smoki.kikiriki.korpa;

import ba.smoki.kikiriki.prodavnica.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ba.smoki.kikiriki.prodavnica.WebShopServlet.PRODUCTS;

@WebServlet(name = "shoppingCartServlet", urlPatterns = "/korpa")
public class ShoppingCartServlet extends HttpServlet {

    public static final String KORPA = "KORPA";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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

            HttpSession session = request.getSession();
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(KORPA);
            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                session.setAttribute(KORPA, shoppingCart);
            }
            String[] productIdArray = request.getParameterValues("productId");
            List<Long> productIds = Stream.of(productIdArray)
                    .map(param -> Long.parseLong(param))
                    .collect(Collectors.toList());
            String[] quantityArray = request.getParameterValues("quantity");
            List<Integer> quantities = Stream.of(quantityArray)
                    .map(quantity -> quantity.isEmpty() ? 0 : Integer.parseInt(quantity))
                    .collect(Collectors.toList());
            List<Product> products = (List<Product>) getServletContext().getAttribute(PRODUCTS);
            for (int i = 0; i < productIds.size(); i++) {
                Long productId = productIds.get(i);
                Integer quantity = quantities.get(i);
                Product product = products
                        .stream()
                        .filter(p -> p.getId().equals(productId))
                        .findFirst()
                        .orElseThrow();
                if (quantity != 0) {
                    shoppingCart.addCartItem(product, quantity);
                }
            }
            if (!shoppingCart.getShoppingCartItems().isEmpty()) {

                out.println("<form action='/kikiriki/odjava' method='post'>");

                out.println("<h1>Artikli u korpi</h1>");
                out.println("<table>");
                out.println("<tr bgcolor='maroon'>" +
                            "<th>Naziv</th>" +
                            "<th>Cijena</th>" +
                            "<th>Količina</th>" +
                            "<th>Ukupno</th>" +
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
                BigDecimal total = shoppingCart
                        .getShoppingCartItems()
                        .stream()
                        .map(ShoppingCartItem::getTotalPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                out.println("<br/><h3>Sve ukupno: " + total + "</h3>");


                BigDecimal ukljucujuciPDV = shoppingCart
                        .getShoppingCartItems()
                        .stream()
                        .map(ShoppingCartItem::getPDV)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal samoPDV = ukljucujuciPDV.subtract(total);
                out.println("<h3>PDV: " + samoPDV + "</h3>");
                out.println("<h3>Cijena sa PDV: " + ukljucujuciPDV + "</h3><br/>");

                out.println("<button class='btn-login'>Kupi</button>");
                out.println("</form>");

                out.println("<a href='/kikiriki/prodavnica' class='btn-new' target='_self'>Nastavi kupovinu</a");


            } else {
                out.println("<form action='/kikiriki/prodavnica' method='post'>");
                out.println("<hr/><h1>Nema proizvoda u korpi</h1><hr/>");
                out.println("<button class='btn-login'>Nastavi kupovinu</button>");
                out.println("</form>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</main>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
