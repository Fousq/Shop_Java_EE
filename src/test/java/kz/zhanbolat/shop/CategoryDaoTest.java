package kz.zhanbolat.shop;

import kz.zhanbolat.shop.dao.impl.CategoryDaoImpl;
import kz.zhanbolat.shop.entity.Category;
import kz.zhanbolat.shop.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CategoryDaoTest {
    private static final Logger logger = LogManager.getLogger(CategoryDaoTest.class);
    private static SeContainer container;
    private CategoryDaoImpl categoryDao;

    @BeforeClass
    public static void setUp() {
        container = SeContainerInitializer.newInstance().initialize();
    }

    @Before
    public void init() {
        categoryDao = container.select(CategoryDaoImpl.class).get();
    }

    @Test
    public void findAllShouldNotReturnEmptyList() {
        List<Category> categories = categoryDao.findAll();
        logger.debug(categories);
        assertNotEquals(Collections.EMPTY_LIST, categories);
    }

    @Test
    public void createShouldNotThrowException() {
        categoryDao.create(new Category("testCategory"));
        List<Category> categoryList = categoryDao.findAll();
        Category category = categoryList.get(categoryList.size() - 1);
        logger.debug(category);
        assertEquals("testCategory", category.getName());
    }

    @Test(expected = DaoException.class)
    public void createShouldThrowDaoException() {
        categoryDao.create(new Category("category1"));
    }

    @Test
    public void deleteShouldNotThrowException() {
        categoryDao.create(new Category("categoryToBeDeleted"));
        List<Category> categories = categoryDao.findAll();
        Category category = categories.get(categories.size() - 1);
        categoryDao.delete(category.getName());
        List<Category> categoriesAfterDeleting = categoryDao.findAll();

        assertNotEquals(categories.size(), categoriesAfterDeleting.size());
    }

    @AfterClass
    public static void tearDown() {
        container.close();
    }
}
