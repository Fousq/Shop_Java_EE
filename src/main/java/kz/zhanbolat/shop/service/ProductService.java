package kz.zhanbolat.shop.service;

import kz.zhanbolat.shop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getProductsOfCategory(String categoryName);
}
