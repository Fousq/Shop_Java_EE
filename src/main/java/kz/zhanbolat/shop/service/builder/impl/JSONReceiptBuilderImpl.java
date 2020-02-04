package kz.zhanbolat.shop.service.builder.impl;

import kz.zhanbolat.shop.entity.Receipt;
import kz.zhanbolat.shop.service.builder.ReceiptBuilder;
import kz.zhanbolat.shop.service.builder.ReceiptForm;
import kz.zhanbolat.shop.service.dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.*;

@Stateless
@ReceiptForm(ReceiptForm.ReceiptType.JSON)
/**
 * Should be user in rest layer
 */
public class JSONReceiptBuilderImpl implements ReceiptBuilder {
    private static final Logger logger = LogManager.getLogger(JSONReceiptBuilderImpl.class);
    private Set<ProductDTO> purchasedProductList;
    private BigDecimal totalSum;

    public JSONReceiptBuilderImpl() {
        purchasedProductList = new HashSet<>();
    }

    @Override
    public Receipt build() {
        Map<ProductDTO, String> productsTotalPriceMap = new HashMap<>();
        purchasedProductList.forEach(purchasedProduct ->
                productsTotalPriceMap.put(purchasedProduct, calculateTotalPrice(purchasedProduct).toString()));
        return new Receipt(productsTotalPriceMap, totalSum.toString());
    }

    @Override
    public ReceiptBuilder setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    @Override
    public ReceiptBuilder addProduct(ProductDTO product) {
        purchasedProductList.add(product);
        return this;
    }

    @Override
    public ReceiptBuilder addListOfProduct(List<ProductDTO> productList) {
        purchasedProductList.addAll(productList);
        return this;
    }

    private BigDecimal calculateTotalPrice(ProductDTO product) {
        return BigDecimal.valueOf(product.getPrice() * product.getSequence());
    }
}
