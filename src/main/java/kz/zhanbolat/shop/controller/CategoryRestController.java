package kz.zhanbolat.shop.controller;

import kz.zhanbolat.shop.entity.Category;
import kz.zhanbolat.shop.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestController {
    @Inject
    private CategoryService categoryService;

    @GET
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
}
