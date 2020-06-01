package org.notail.mall.service.impl;

import org.notail.mall.dao.CategoryDao;
import org.notail.mall.dao.impl.CategoryDaoImpl;
import org.notail.mall.pojo.Category;
import org.notail.mall.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao =  new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
