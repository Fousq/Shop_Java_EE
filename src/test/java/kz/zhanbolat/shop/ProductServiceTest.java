package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private static final Logger logger = LogManager.getLogger(ProductServiceTest.class);
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductDao productDao;

    @Test
    public void testGetProductsShouldReturnListOfProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "test1", 1.0, "test1"));
        products.add(new Product(2, "test2", 1.0, "test2"));
        when(productDao.findAll()).thenReturn(products);

        List<Product> productList = productService.getProducts();

        assertNotNull(productList);
        assertEquals(2, productList.size());
    }

    @Test
    public void testGetProductsByCategoryShouldReturnListOfProducts() {
        List<Product> productsOfTest1 = new ArrayList<>();
        List<Product> productsOfTest2 = new ArrayList<>();
        productsOfTest1.add(new Product(1, "test1", 1.0, "test1"));
        productsOfTest1.add(new Product(2, "test2", 1.0, "test1"));
        productsOfTest2.add(new Product(3, "test3", 1.0, "test2"));
        when(productDao.findAllByCategory("test1")).thenReturn(productsOfTest1);
        when(productDao.findAllByCategory("test2")).thenReturn(productsOfTest2);

        List<Product> productsOfCategoryTest1 = productService.getProductsOfCategory("test1");
        List<Product> productsOfCategoryTest2 = productService.getProductsOfCategory("test2");

        assertEquals(productsOfTest1.size(), productsOfCategoryTest1.size());
        assertEquals(productsOfTest2.size(), productsOfCategoryTest2.size());
    }
}
