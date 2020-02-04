package kz.zhanbolat.shop.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Receipt {
    private Map<Product, Integer> purchasedProducts;
    private BigDecimal totalPrice;

    public Receipt() {
    }

    public Receipt(Map<Product, Integer> purchasedProducts, BigDecimal totalPrice) {
        this.purchasedProducts = purchasedProducts;
        this.totalPrice = totalPrice;
    }

    public Map<Product, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<Product, Integer> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Receipt.class.getSimpleName() + "[", "]")
                .add("purchasedProducts=" + purchasedProducts)
                .add("totalPrice='" + totalPrice + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(purchasedProducts, receipt.purchasedProducts) &&
                Objects.equals(totalPrice, receipt.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasedProducts, totalPrice);
    }
}
