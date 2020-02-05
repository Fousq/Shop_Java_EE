package kz.zhanbolat.shop.service;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.entity.Receipt;

import java.math.BigDecimal;
import java.util.Map;

public interface PurchaseService {
    void purchaseProduct(Integer productId, Integer quantity);
    Map<Product, Integer> getListOfProduct();
    Receipt purchase();
    BigDecimal calculateTotalSum();
}
