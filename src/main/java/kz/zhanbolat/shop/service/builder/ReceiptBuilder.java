package kz.zhanbolat.shop.service.builder;

import kz.zhanbolat.shop.entity.Receipt;
import kz.zhanbolat.shop.service.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptBuilder {
    Receipt build();
    ReceiptBuilder setTotalSum(BigDecimal totalSum);
    ReceiptBuilder addProduct(ProductDTO product);
    ReceiptBuilder addListOfProduct(List<ProductDTO> productList);
}
