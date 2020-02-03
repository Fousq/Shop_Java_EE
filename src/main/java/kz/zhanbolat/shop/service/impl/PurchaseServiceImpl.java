package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.PurchaseService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class PurchaseServiceImpl implements PurchaseService {
    @Inject
    private ProductDao productDao;
    private Set<Product> purchasedProducts;

    public PurchaseServiceImpl() {
        purchasedProducts = new HashSet<>();
    }

    @Override
    /**
     * @param productId - id of product entity
     * @throw NoProductFoundException - throw in situation where product wasn't found
     */
    public void purchaseProduct(Integer productId) {
        Product product = productDao.findOne(productId);
        purchasedProducts.add(product);
    }

    @Override
    public List<Product> getListOfProduct() {
        return new ArrayList<>(purchasedProducts);
    }
}
