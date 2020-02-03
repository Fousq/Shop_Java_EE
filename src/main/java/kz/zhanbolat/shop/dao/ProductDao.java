package kz.zhanbolat.shop.dao;

import kz.zhanbolat.shop.entity.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product, Integer> {
    List<Product> findAllByCategory(String categoryName);
}
