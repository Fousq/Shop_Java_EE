package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.PurchaseService;
import kz.zhanbolat.shop.service.dto.ProductDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class PurchaseServiceImpl implements PurchaseService {
    private Set<ProductDTO> purchasedProducts;

    public PurchaseServiceImpl() {
        purchasedProducts = new HashSet<>();
    }

    @Override
    /**
     * @param productId - id of product entity
     * @throw NoProductFoundException - throw in situation where product wasn't found
     */
    public void purchaseProduct(ProductDTO product) {
        purchasedProducts.add(product);
    }

    @Override
    public List<ProductDTO> getListOfProduct() {
        return new ArrayList<>(purchasedProducts);
    }

    @Override
    public BigDecimal purchase() {
        List<BigDecimal> prices = purchasedProducts.stream()
                .map(this::calculateTotalPrice)
                .collect(Collectors.toList());
        purchasedProducts.clear();
        return calculateTotalSum(prices);
    }

    private BigDecimal calculateTotalPrice(ProductDTO product) {
        return BigDecimal.valueOf(product.getPrice() * product.getSequence());
    }

    private BigDecimal calculateTotalSum(List<BigDecimal> prices) {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            totalSum = totalSum.add(price);
        }
        return totalSum;
    }
}
