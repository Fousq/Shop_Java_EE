package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.service.ProductService;

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
    @Inject
    private ProductService productService;

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
