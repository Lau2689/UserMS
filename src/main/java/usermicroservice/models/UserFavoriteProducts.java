package usermicroservice.models;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Builder
@Entity
@Table(name = "usersfavoriteproducts")
public class UserFavoriteProducts {
    private String userEmail;
    private int productId;
    @OneToMany(mappedBy = "users")
    Set<User> users;

    public UserFavoriteProducts(){}

    public UserFavoriteProducts(String userEmail, int productId, Set<User> users) {
        this.userEmail = userEmail;
        this.productId = productId;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserFavoriteProducts{" +
                "userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                ", users=" + users +
                '}';
    }
}
