package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.entity.Receipt;
import kz.zhanbolat.shop.service.PurchaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

@Stateless
public class PurchaseServiceImpl implements PurchaseService {
    @Inject
    private ProductDao productDao;
    private Map<Product, Integer> purchasedProducts;

    public PurchaseServiceImpl() {
        purchasedProducts = new HashMap<>();
    }

    @Override
    /**
     * @param productName - name of product entity
     * @param quantity - the amount of product to purchase
     * @throw NoProductFoundException - throw in situation where product wasn't found
     */
    public void purchaseProduct(String productName, Integer quantity) {
        Product product = productDao.findOneByName(productName);
        if (!purchasedProducts.containsKey(product)) {
            purchasedProducts.put(product, quantity);
        } else {
            throw new IllegalStateException("Don't buy the same product");// will be changed
        }
    }

    @Override
    public Map<Product, Integer> getListOfProduct() {
        return purchasedProducts;
    }

    @Override
    public Receipt purchase() {
        Receipt receipt = new Receipt(Map.copyOf(purchasedProducts), calculateTotalSum());
        purchasedProducts.clear();
        return receipt;
    }

    @Override
    public BigDecimal calculateTotalSum() {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Product purchasedProduct : purchasedProducts.keySet()) {
            totalSum = totalSum.add(calculateTotalPrice(purchasedProduct));
        }
        return totalSum;
    }

    private BigDecimal calculateTotalPrice(Product product) {
        return BigDecimal.valueOf(product.getPrice() * purchasedProducts.get(product));
    }
}
