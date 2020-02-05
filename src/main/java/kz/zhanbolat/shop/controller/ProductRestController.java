package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductRestController {
    private static final Logger logger = LogManager.getLogger(ProductRestController.class);
    @Inject
    private ProductService productService;

    public ProductRestController() {
    }

    @GET
    public List<Product> getListOfProduct() {
        return productService.getProducts();
    }

    @GET
    @Path("category/{category}")
    public List<Product> getListOfProductByCategory(@PathParam("category") String categoryName) {
        return productService.getProductsOfCategory(categoryName);
    }
}
