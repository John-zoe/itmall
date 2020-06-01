package org.notail.mall.dao;

import org.notail.mall.pojo.Category;
import org.notail.mall.pojo.Product;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();

}
