package ba.smoki.kikiriki.korpa;

import ba.smoki.kikiriki.prodavnica.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartItem implements Serializable {
    private int quantity;
    private Product product;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(quantity).multiply(product.getPrice());
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        return totalPrice;
    }
    public BigDecimal getPDV() {
        BigDecimal PDV = getTotalPrice().multiply(BigDecimal.valueOf(1.17));
        PDV = PDV.setScale(2, RoundingMode.HALF_UP);
        return PDV;
    }


}
