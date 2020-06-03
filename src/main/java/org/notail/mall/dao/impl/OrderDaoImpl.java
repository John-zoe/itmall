package org.notail.mall.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.runner.Runner;
import org.notail.mall.dao.OrderDao;
import org.notail.mall.pojo.Orders;
import org.notail.mall.utils.JDBCUtil;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OrderDaoImpl implements OrderDao {
    QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public int insertOrder(Orders orders) {
        String sql = "insert into orders values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int orderId = 0;
        try {
             runner.update(sql, orders.getNumber(), orders.getBuyer_id(), orders.getTotal_amount(), orders.getTotal_price(), orders.getPayment_price(), orders.getRemark(), orders.getConcat(), orders.getMobile(), orders.getStreet(), orders.getZipcode(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orders.getCreate_time()), orders.getPayment_time(), orders.getDelivery_time(), orders.getEnd_time(), orders.getStatus());
            BigInteger lid = runner.query("select last_insert_id()",new ScalarHandler<BigInteger>());  //获取最近一次添加语句所产生的自增列的值
            orderId = lid.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }
}
