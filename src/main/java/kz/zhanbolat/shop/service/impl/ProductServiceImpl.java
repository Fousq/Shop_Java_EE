package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    @Override
    /**
    * @param categoryName - Name of the category to search for products
    * @return List of found products
    * @throw NoProductFoundException - throw in situations
    *                                  where products with such category name weren't found
    */
    public List<Product> getProductsOfCategory(String categoryName) {
        return productDao.findAllByCategory(categoryName);
    }
}
