package com.company.ShoppingCart.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="shoppingCart")
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private Integer quantity;
    @NotEmpty
    private String category;
    @NotNull
    private Boolean isDomestic;
    private String imgUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getDomestic() {
        return isDomestic;
    }

    public void setDomestic(Boolean domestic) {
        isDomestic = domestic;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopping shopping = (Shopping) o;
        return Objects.equals(id, shopping.id) &&
                Objects.equals(name, shopping.name) &&
                Objects.equals(price, shopping.price) &&
                Objects.equals(quantity, shopping.quantity) &&
                Objects.equals(category, shopping.category) &&
                Objects.equals(isDomestic, shopping.isDomestic) &&
                Objects.equals(imgUrl, shopping.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, category, isDomestic, imgUrl);
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", isDomestic=" + isDomestic +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
