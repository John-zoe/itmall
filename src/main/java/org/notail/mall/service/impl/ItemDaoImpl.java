package org.notail.mall.service.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.notail.mall.dao.ItemDao;
import org.notail.mall.pojo.Item;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public void addItem(Item item) {
        String sql = "insert into item values(null,?,?,?,?,?)";
        try {
            runner.update(sql,item.getOrder_id(),item.getProduct_id(),item.getAmount(),item.getTotal_price(),item.getPayment_price());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
