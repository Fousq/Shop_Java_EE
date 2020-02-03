package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.factory.SqlMapFactory;
import kz.zhanbolat.shop.dao.impl.ProductDaoImpl;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.exception.DaoException;
import kz.zhanbolat.shop.exception.NoProductFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest {
    private static final Logger logger = LogManager.getLogger(ProductDaoTest.class);
    private ProductDaoImpl productDao;
    private SqlMapFactory factory;
    private static SeContainer container;

    @BeforeClass
    public static void setUp() {
        container = SeContainerInitializer.newInstance().initialize();
    }

    @Before
    public void init() {
        productDao = container.select(ProductDaoImpl.class).get();
    }

    @Test
    public void testFindAllShouldReturnNotEmptyList() {
        List<Product> products = productDao.findAll();
        logger.debug(products);

        assertNotEquals(Collections.EMPTY_LIST, products);
    }

    @Test
    public void testFindOneShouldNotThrowException() {
        Product product = productDao.findOne(1);
        logger.debug(product);
        assertNotNull(product);
    }

    @Test(expected = NoProductFoundException.class)
    public void testFindOneShouldThrowNoProductFoundException() {
        Product product = productDao.findOne(Integer.MAX_VALUE);
        logger.debug(product);
        assertNull(product);
    }

    @Test
    public void testCreateProductShouldNotThrowAnyException() {
        productDao.create(new Product("testProductWithoutCategory", 1.0));
        productDao.create(new Product("testProductWithCategory", 1.0, "category1"));
        List<Product> products = productDao.findAll();
        Product productWithoutCategory = products.get(products.size() - 2);
        Product productWithCategory = products.get(products.size() - 1);

        assertNotNull(productWithCategory);
        assertNotNull(productWithoutCategory);
        assertEquals("testProductWithoutCategory", productWithoutCategory.getName());
        assertNull(productWithoutCategory.getCategoryName());
        assertEquals("testProductWithCategory", productWithCategory.getName());
        assertEquals("category1", productWithCategory.getCategoryName());
    }

    @Test(expected = DaoException.class)
    public void testCreateProductShouldThrowDaoException() {
        productDao.create(new Product("testProduct1", 1.0, "non-existed_category"));
    }

    @Test
    public void testUpdateProductName() {
        productDao.create(new Product("testProductNameToBeUpdated", 1.0,"category1"));
        List<Product> products = productDao.findAll();
        Product product = products.get(products.size() - 1);
        product.setName("updatedProduct");
        productDao.update(product);
        Product updatedProduct = productDao.findOne(product.getId());
        logger.debug(updatedProduct);
        assertEquals("updatedProduct", updatedProduct.getName());
    }

    @Test
    public void testUpdateProductCategoryNameShouldNotThrowException() {
        productDao.create(new Product("testProductCategoryNameToBeUpdated",
                                       1.0,
                                "category1"));
        List<Product> products = productDao.findAll();
        Product product = products.get(products.size() - 1);
        product.setCategoryName("category2");
        productDao.update(product);
        Product updatedProduct = productDao.findOne(product.getId());
        logger.debug(updatedProduct);

        assertEquals("category2", updatedProduct.getCategoryName());
    }

    @Test(expected = DaoException.class)
    public void testUpdateProductCategoryNameShouldThrowException() {
        productDao.create(new Product("testProductCategoryName", 1.0,"category1"));
        List<Product> products = productDao.findAll();
        Product product = products.get(products.size() - 1);
        product.setCategoryName("non-exist_category");
        productDao.update(product);
    }

    @Test(expected = NoProductFoundException.class)
    public void testDeleteProductShouldThrowNoProductFoundException() {
        productDao.create(new Product("testProductToBeDeleted", 1.0));
        List<Product> products = productDao.findAll();
        Product product = products.get(products.size() - 1);
        productDao.delete(product.getId());
        Product deletedProduct = productDao.findOne(product.getId());
    }

    @Test
    public void testFindAllByCategoryNameShouldNotReturnEmptyList() {
        List<Product> products = productDao.findAllByCategory("category1");
        logger.debug(products);
        assertNotNull(products);
        assertNotEquals(Collections.EMPTY_LIST, products);
    }

    @AfterClass
    public static void tearDown() {
        container.close();
    }
}
