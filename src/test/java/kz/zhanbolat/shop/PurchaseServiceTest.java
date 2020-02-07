package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.ProductDao;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.entity.Receipt;
import kz.zhanbolat.shop.exception.NoProductFoundException;
import kz.zhanbolat.shop.exception.SameProductPurchaseException;
import kz.zhanbolat.shop.service.impl.PurchaseServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;
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
        when(productDao.findOneByName(anyString()))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));

        purchaseService.purchaseProduct("test1", 1);
    }

    @Test(expected = NoProductFoundException.class)
    public void testPurchaseProductShouldThrowException() {
        when(productDao.findOneByName(anyString())).thenThrow(new NoProductFoundException());

        purchaseService.purchaseProduct("no name", 1);
    }

    @Test
    public void testGetListOfProductShouldReturnNotEmptyList() {
        when(productDao.findOneByName("test1"))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));
        when(productDao.findOneByName("test2"))
                .thenReturn(new Product(2, "test2", 1.0, "test2"));
        purchaseService.purchaseProduct("test1", 1);
        purchaseService.purchaseProduct("test2", 2);
        Map<Product, Integer> products = purchaseService.getListOfProduct();

        assertEquals(2, products.size());
    }

    @Test(expected = SameProductPurchaseException.class)
    public void testPurchaseShouldThrowException() {
        when(productDao.findOneByName("test1"))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));
        purchaseService.purchaseProduct("test1", 1);
        purchaseService.purchaseProduct("test1", 2);
    }

    @Test
    public void testCalculateTotalSumShouldReturnExpectedTotalSum() {
        when(productDao.findOneByName("test1"))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));
        when(productDao.findOneByName("test2"))
                .thenReturn(new Product(2, "test2", 2.0, "test2"));
        purchaseService.purchaseProduct("test1", 1);
        purchaseService.purchaseProduct("test2", 2);
        BigDecimal expectedTotalSum = BigDecimal.valueOf(5.0);
        BigDecimal totalSum = purchaseService.calculateTotalSum();

        assertEquals(expectedTotalSum, totalSum);
    }

    @Test
    public void testPurchaseShouldReturnNonEmptyMap() {
        when(productDao.findOneByName("test1"))
                .thenReturn(new Product(1, "test1", 1.0, "test1"));
        when(productDao.findOneByName("test2"))
                .thenReturn(new Product(2, "test2", 2.0, "test2"));
        purchaseService.purchaseProduct("test1", 1);
        purchaseService.purchaseProduct("test2", 2);
        BigDecimal expectedTotalSum = BigDecimal.valueOf(5.0);
        Receipt purchase = purchaseService.purchase();
        BigDecimal totalPrice = purchase.getTotalPrice();
        Map<Product, Integer> purchasedProducts = purchase.getPurchasedProducts();

        assertNotNull(purchasedProducts);
        assertNotEquals(Collections.EMPTY_MAP, purchasedProducts);
        assertEquals(expectedTotalSum, totalPrice);
    }
}
