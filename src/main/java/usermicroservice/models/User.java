package usermicroservice.models;

import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    //@Email(message = "Email is not valid", regexp = "^[[^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;
    @NotNull
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotNull
    @NotBlank(message = "PaymentMethod is mandatory")
    private String paymentMethod;

    private Integer fidelityPoints;

    private Double purchasePriceAverage;

    public User() {}

    public User(String email, String name, String lastName, String city, String paymentMethod, Integer fidelityPoints, Double purchasePriceAverage) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.city = city;
        this.paymentMethod = paymentMethod;
        this.fidelityPoints = fidelityPoints;
        this.purchasePriceAverage = purchasePriceAverage;
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

    @Override
    public String toString() {
        return "User{" +
                "email=" + email +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", fidelityPoints=" + fidelityPoints +
                ", purchasePriceAverage=" + purchasePriceAverage +
                '}';
    }
}