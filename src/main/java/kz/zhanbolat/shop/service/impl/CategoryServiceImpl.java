package kz.zhanbolat.shop.service.impl;

import kz.zhanbolat.shop.dao.BaseDao;
import kz.zhanbolat.shop.entity.Category;
import kz.zhanbolat.shop.service.CategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private BaseDao<Category, String> categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
