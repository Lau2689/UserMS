package usermicroservice.models;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String paymentMethod;

    private Integer fidelityPoints;
    private Double purchasePriceAverage;
    @OneToMany
    private List<Integer> favoriteProducts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private Set<UserFavoriteProducts> favorites = new LinkedHashSet<>();


    public User() {
    }

    public User(String email, String name, String lastName, String city, String paymentMethod, Integer fidelityPoints, Double purchasePriceAverage, List<Integer> favoriteProducts, Set<UserFavoriteProducts> favorites) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.paymentMethod = paymentMethod;
        this.fidelityPoints = fidelityPoints;
        this.purchasePriceAverage = purchasePriceAverage;
        this.favoriteProducts = favoriteProducts;
        this.favorites = favorites;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getFidelityPoints() {
        return fidelityPoints;
    }

    public void setFidelityPoints(Integer fidelityPoints) {
        this.fidelityPoints = fidelityPoints;
    }

    public Double getPurchasePriceAverage() {
        return purchasePriceAverage;
    }

    public void setPurchasePriceAverage(Double purchasePriceAverage) {
        this.purchasePriceAverage = purchasePriceAverage;
    }

    public List<Integer> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<Integer> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public Set<UserFavoriteProducts> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<UserFavoriteProducts> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", fidelityPoints=" + fidelityPoints +
                ", purchasePriceAverage=" + purchasePriceAverage +
                ", favoriteProducts=" + favoriteProducts +
                ", favorites=" + favorites +
                '}';
    }
}

