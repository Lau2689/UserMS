package usermicroservice.models;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Cart {
    @Id
    private int cartId;

    private String userId;

    private List<Product> products;

    public Cart(){}

    public Cart(int cartId, String userId, List<Product> products) {
        this.cartId = cartId;
        this.userId = userId;
        this.products = products;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId='" + userId + '\'' +
                ", products=" + products +
                '}';
    }
}
