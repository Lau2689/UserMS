package usermicroservice.models;

import com.sun.istack.NotNull;
import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "usersfavoriteproducts")
public class UserFavoriteProducts {


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iduserproduct;
    @NotNull
    private String userEmail;
    @NotNull
    private int productId;


    public UserFavoriteProducts(){}

    public UserFavoriteProducts(int iduserproduct, String userEmail, int productId) {
        this.iduserproduct = iduserproduct;
        this.userEmail = userEmail;
        this.productId = productId;
    }

    public int getIduserproduct() {
        return iduserproduct;
    }

    public void setIduserproduct(int iduserproduct) {
        this.iduserproduct = iduserproduct;
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
                "iduserproduct=" + iduserproduct +
                ", userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                '}';
    }
}
