package kz.zhanbolat.shop.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class Product implements Serializable {
    private Integer id;
    private String name;
    private String categoryName;

    public Product() {
    }

    public Product(Integer id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("categoryName='" + categoryName + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(categoryName, product.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryName);
    }
}
