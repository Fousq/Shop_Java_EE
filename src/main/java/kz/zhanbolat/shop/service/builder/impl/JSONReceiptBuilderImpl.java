package kz.zhanbolat.shop.service.builder.impl;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.builder.ReceiptBuilder;
import kz.zhanbolat.shop.service.builder.ReceiptFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.*;

@ApplicationScoped
@ReceiptFormat(ReceiptFormat.FormatType.JSON)
public class JSONReceiptBuilderImpl implements ReceiptBuilder<JsonObject> {
    private static final Logger logger = LogManager.getLogger(JSONReceiptBuilderImpl.class);
    private static final String PRODUCTS_NODE = "products";
    private static final String TOTAL_SUM_NODE = "totalSum";
    private static final String PRODUCT_ID_NODE = "id";
    private static final String PRODUCT_NAME_NODE = "name";
    private static final String PRODUCT_PRICE_NODE = "price";
    private static final String PRODUCT_QUANTITY_NODE = "quantity";
    private Map<Product, Integer> purchasedProductList;
    private BigDecimal totalSum;

    public JSONReceiptBuilderImpl() {
        purchasedProductList = new HashMap<>();
    }

    @Override
    public JsonObject build() {
        if (purchasedProductList.isEmpty() || totalSum == null) {
            throw new IllegalStateException("Receipt cannot be formed. " +
                                            "No purchased products or total summery");
        }
        return Json.createObjectBuilder()
                .add(PRODUCTS_NODE, createProductQuantityArrayJsonBuilder(purchasedProductList))
                .add(TOTAL_SUM_NODE, totalSum)
                .build();
    }

    private JsonArrayBuilder createProductQuantityArrayJsonBuilder(Map<Product, Integer> purchasedProductList) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        int i = 1;
        for (Product product : purchasedProductList.keySet()) {
            JsonObjectBuilder productJsonBuilder = createProductJsonBuilder(i,
                                                                            product,
                                                                            purchasedProductList.get(product));
            builder.add(productJsonBuilder);
            i++;
        }
        return builder;
    }

    private JsonObjectBuilder createProductJsonBuilder(Integer id, Product product, Integer quantity) {
        return Json.createObjectBuilder().add(PRODUCT_ID_NODE, id)
                                         .add(PRODUCT_NAME_NODE, product.getName())
                                         .add(PRODUCT_PRICE_NODE, product.getPrice())
                                         .add(PRODUCT_QUANTITY_NODE, quantity);
    }

    @Override
    public ReceiptBuilder setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
        return this;
    }

    @Override
    public ReceiptBuilder addProduct(Product product, Integer quantity) {
        purchasedProductList.put(product, quantity);
        return this;
    }

    @Override
    public ReceiptBuilder addListOfProduct(Map<Product, Integer> productList) {
        purchasedProductList.putAll(productList);
        return this;
    }
}
