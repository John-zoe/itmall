package org.notail.mall.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.notail.mall.dao.CategoryDao;
import org.notail.mall.pojo.Category;
import org.notail.mall.pojo.Product;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql ="select * from category";

        try {
            List<Category> categoryList = runner.query(sql, new BeanListHandler<>(Category.class));
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
