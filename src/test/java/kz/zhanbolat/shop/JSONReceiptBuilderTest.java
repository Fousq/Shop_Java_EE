package kz.zhanbolat.shop;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.builder.ReceiptBuilder;
import kz.zhanbolat.shop.service.builder.impl.JSONReceiptBuilderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JSONReceiptBuilderTest {
    private static final Logger logger = LogManager.getLogger(JSONReceiptBuilderTest.class);
    private ReceiptBuilder<JsonObject> receiptBuilder;

    @Before
    public void setUp() {
        receiptBuilder = new JSONReceiptBuilderImpl();
    }

    @Test
    public void testBuildShouldNotThrowAnyException() {
        Map<Product, Integer> purchasedProduct = new HashMap<>();
        purchasedProduct.put(new Product("product1", 1.0), 1);
        purchasedProduct.put(new Product("product2", 2.0), 2);

        JsonObject jsonObject = receiptBuilder.addListOfProduct(purchasedProduct).setTotalSum(BigDecimal.ONE).build();
        JsonArray products = jsonObject.getJsonArray("products");
        JsonObject product1 = products.get(0).asJsonObject();
        JsonObject product2 = products.get(1).asJsonObject();
        logger.debug(jsonObject.toString());

        assertNotEquals(0, products.size());
        assertEquals(BigDecimal.ONE, jsonObject.getJsonNumber("totalSum").bigDecimalValue());

        assertEquals(1, product1.getInt("id"));
        assertEquals("product1", product1.getString("name"));
        assertEquals(BigDecimal.valueOf(1.0), product1.getJsonNumber("price").bigDecimalValue());
        assertEquals(1, product1.getInt("quantity"));

        assertEquals(2, product2.getInt("id"));
        assertEquals("product2", product2.getString("name"));
        assertEquals(BigDecimal.valueOf(2.0), product2.getJsonNumber("price").bigDecimalValue());
        assertEquals(2, product2.getInt("quantity"));
    }

    @Test(expected = RuntimeException.class)
    public void testBuildShouldThrowExceptionOnTotalSumIsNull() {
        Map<Product, Integer> purchasedProduct = new HashMap<>();
        purchasedProduct.put(new Product("product1", 1.0), 1);
        purchasedProduct.put(new Product("product2", 2.0), 2);
        receiptBuilder.addListOfProduct(purchasedProduct).build();
    }

    @Test(expected = RuntimeException.class)
    public void testBuildShouldThrowExceptionOnPurchasedProductListIsEmpty() {
        receiptBuilder.setTotalSum(BigDecimal.ONE).build();
    }

    @Test
    public void testAddListOfProductWithTheSameMap() {
        Map<Product, Integer> purchasedProduct = new HashMap<>();
        purchasedProduct.put(new Product("product1", 1.0), 1);
        purchasedProduct.put(new Product("product2", 2.0), 2);
        Map<Product, Integer> copy = Map.copyOf(purchasedProduct);
        receiptBuilder.addListOfProduct(purchasedProduct);
        receiptBuilder.addListOfProduct(copy);
        JsonObject jsonObject = receiptBuilder.setTotalSum(BigDecimal.ONE).build();
        logger.debug(jsonObject.toString());

        JsonArray products = jsonObject.getJsonArray("products");
        assertEquals(2, products.size());
    }
}
