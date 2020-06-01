package org.notail.mall.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.notail.mall.dao.ProductDao;
import org.notail.mall.pojo.Product;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public long selectTotalCount(int cateId) {
        String sql = "select count(*) from product where cate_id = ?";
        Long totalCount = 0L;
        try {
            //count(*) use ScalarHandler<Long>()
            totalCount = runner.query(sql, new ScalarHandler<Long>(), cateId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCount;
    }

    @Override
    public List<Product> selectByPage(int cateId, int start, int pageSize) {
        String sql = "select * from product where cate_id=? limit ?,?";
        try {
            List<Product> productList = runner.query(sql, new BeanListHandler<>(Product.class),cateId, start, pageSize);
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
