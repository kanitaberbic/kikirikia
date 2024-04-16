package ba.smoki.kikiriki.three;

import ba.smoki.kikiriki.two.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {

    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    public void addCartItem(Product product, int quantity) {
        if (quantity == 0){
            return;
        }
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (shoppingCartItem.getProduct().getId() == product.getId()) {
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + quantity);
                if(shoppingCartItem.getQuantity()<=0){
                    shoppingCartItems.remove(shoppingCartItem);
                }
                return;
            }
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(quantity, product);
        shoppingCartItems.add(shoppingCartItem);
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }
}
