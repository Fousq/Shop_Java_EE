package kz.zhanbolat.shop.service;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseService {
    void purchaseProduct(ProductDTO product);
    List<ProductDTO> getListOfProduct();
    BigDecimal purchase();
}
