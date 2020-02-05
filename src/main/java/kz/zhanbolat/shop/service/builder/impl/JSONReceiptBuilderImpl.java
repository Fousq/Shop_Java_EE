package kz.zhanbolat.shop.service.builder.impl;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.builder.ReceiptBuilder;
import kz.zhanbolat.shop.service.builder.ReceiptForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.math.BigDecimal;
import java.util.*;

@Stateless
@ReceiptForm(ReceiptForm.ReceiptType.JSON)
public class JSONReceiptBuilderImpl implements ReceiptBuilder<JsonObject> {
    private static final Logger logger = LogManager.getLogger(JSONReceiptBuilderImpl.class);
    private Map<Product, Integer> purchasedProductList;
    private BigDecimal totalSum;

    public JSONReceiptBuilderImpl() {
        purchasedProductList = new HashMap<>();
    }

    @Override
    public JsonObject build() {
        if (purchasedProductList.isEmpty() || totalSum != null) {
            throw new IllegalStateException("Receipt exception");//message will be changed later
        }
        return Json.createObjectBuilder()
                .add("products", createProductQuantityArrayJsonBuilder(purchasedProductList))
                .add("totalSum", totalSum).build();
    }

    private JsonArrayBuilder createProductQuantityArrayJsonBuilder(Map<Product, Integer> purchasedProductList) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (Product product : purchasedProductList.keySet()) {
            JsonObjectBuilder productJsonBuilder = createProductJsonBuilder(product,
                                                                            purchasedProductList.get(product));
            builder.add(productJsonBuilder);
        }
        return builder;
    }

    private JsonObjectBuilder createProductJsonBuilder(Product product, Integer quantity) {
        return Json.createObjectBuilder().add("id", product.getId()).add("name", product.getName())
                   .add("price", product.getPrice()).add("quantity", quantity);
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
