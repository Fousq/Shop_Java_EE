package kz.zhanbolat.shop.entity;

import kz.zhanbolat.shop.service.dto.ProductDTO;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Receipt {
    private Map<ProductDTO, String> productTotalPrice;
    private String totalPrice;

    public Receipt() {
    }

    public Receipt(Map<ProductDTO, String> productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Receipt(Map<ProductDTO, String> productTotalPrice, String totalPrice) {
        this.productTotalPrice = productTotalPrice;
        this.totalPrice = totalPrice;
    }

    public Map<ProductDTO, String> getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Map<ProductDTO, String> productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Receipt.class.getSimpleName() + "[", "]")
                .add("productTotalPrice=" + productTotalPrice)
                .add("totalPrice='" + totalPrice + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(productTotalPrice, receipt.productTotalPrice) &&
                Objects.equals(totalPrice, receipt.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTotalPrice, totalPrice);
    }
}
