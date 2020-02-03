package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.exception.NoProductFoundException;
import kz.zhanbolat.shop.service.impl.PurchaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {
    private static final Logger logger = LogManager.getLogger(PurchaseServiceTest.class);
    @InjectMocks
    private PurchaseServiceImpl purchaseService;
    @Mock
    private ProductDao productDao;

    @Test
    public void testPurchaseProductShouldNotThrowException() {
        when(productDao.findOne(anyInt()))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));

        purchaseService.purchaseProduct(1);
    }

    @Test(expected = NoProductFoundException.class)
    public void testPurchaseProductShouldThrowException() {
        when(productDao.findOne(anyInt())).thenThrow(new NoProductFoundException());

        purchaseService.purchaseProduct(Integer.MAX_VALUE);
    }

    @Test
    public void testGetListOfProductShouldReturnNotEmptyList() {
        when(productDao.findOne(1))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));
        when(productDao.findOne(2))
                .thenReturn(new Product(2, "test2", 1.0, "test2"));
        purchaseService.purchaseProduct(1);
        purchaseService.purchaseProduct(2);
        List<Product> products = purchaseService.getListOfProduct();

        assertEquals(2, products.size());
    }
}
