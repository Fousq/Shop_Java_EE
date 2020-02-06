package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.controller.dto.ProductPurchaseForm;
import kz.zhanbolat.shop.entity.Product;
import kz.zhanbolat.shop.entity.Receipt;
import kz.zhanbolat.shop.service.PurchaseService;
import kz.zhanbolat.shop.service.builder.ReceiptBuilder;
import kz.zhanbolat.shop.service.builder.ReceiptFormat;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("purchase")
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseRestController {
    @Inject
    private PurchaseService purchaseService;
    @Inject
    @ReceiptFormat(value = ReceiptFormat.FormatType.JSON)
    private ReceiptBuilder receiptBuilder;
    private Receipt receipt;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response purchaseProduct(ProductPurchaseForm productPurchaseForm) {
        String productName = productPurchaseForm.getName();
        purchaseService.purchaseProduct(productName, productPurchaseForm.getQuantity());
        return Response.accepted().build();
    }

    @GET
    public Response getPurchasedProductList() {
        Map<Product, Integer> purchasedProduct = purchaseService.getListOfProduct();
        List<ProductPurchaseForm> productPurchaseForms = new ArrayList<>();
        for (Product product : purchasedProduct.keySet()) {
            productPurchaseForms.add(map(product, purchasedProduct.get(product)));
        }
        return Response.ok().entity(productPurchaseForms).build();
    }

    @POST
    @Path("products")
    public Response purchaseProducts() {
        receipt = purchaseService.purchase();
        return Response.accepted().build();
    }

    @GET
    @Path("receipt")
    public Response getReceipt() {
        Object jsonReceipt = receiptBuilder.addListOfProduct(receipt.getPurchasedProducts())
                                            .setTotalSum(receipt.getTotalPrice())
                                            .build();
        return Response.ok().entity(jsonReceipt).build();
    }

    private ProductPurchaseForm map(Product product, Integer quantity) {
        return new ProductPurchaseForm(product.getName(), product.getPrice(), quantity);
    }
}
