package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.BaseDao;
import kz.zhanbolat.shop.entity.Category;
import kz.zhanbolat.shop.service.impl.CategoryServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    private static final Logger logger = LogManager.getLogger(CategoryServiceTest.class);
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private BaseDao<Category, String> categoryDao;

    @Test
    public void testFindAllShouldNotReturnEmptyList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));
        when(categoryDao.findAll()).thenReturn(categories);
        List<Category> categoryList = categoryService.findAll();

        assertNotEquals(Collections.EMPTY_LIST, categoryList);
    }
}
