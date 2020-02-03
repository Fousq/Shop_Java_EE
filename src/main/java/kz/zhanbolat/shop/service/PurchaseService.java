package kz.zhanbolat.shop.service;

import kz.zhanbolat.shop.entity.Product;

import java.util.List;

public interface PurchaseService {
    void purchaseProduct(Integer productId);
    List<Product> getListOfProduct();
}
