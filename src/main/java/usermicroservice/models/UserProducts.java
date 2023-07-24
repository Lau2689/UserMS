package usermicroservice.models;

import com.sun.istack.NotNull;
import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "userproducts")
public class UserProducts {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserProduct;
    @NotNull
    private String userEmail;
    @NotNull
    private int productId;


    public UserProducts(){}

    public UserProducts(int idUserProduct, String userEmail, int productId) {
        this.idUserProduct = idUserProduct;
        this.userEmail = userEmail;
        this.productId = productId;
    }

    public int getIdUserProduct() {
        return idUserProduct;
    }

    public void setIdUserProduct(int idUserProduct) {
        this.idUserProduct = idUserProduct;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "UserFavoriteProducts{" +
                "iduserproduct=" + idUserProduct +
                ", userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                '}';
    }
}
