package kz.zhanbolat.shop.service.builder;

import kz.zhanbolat.shop.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ReceiptBuilder<T> {
    T build();
    ReceiptBuilder<T> setTotalSum(BigDecimal totalSum);
    ReceiptBuilder<T> addProduct(Product product, Integer quantity);
    ReceiptBuilder<T> addListOfProduct(Map<Product, Integer> productList);
}
