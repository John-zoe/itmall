package org.notail.mall.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.notail.mall.pojo.Address;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;

public class DbTest {

    public static void main(String[] args) throws SQLException {

        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());

        String select = "select * from address where id = ?";
        String insert = "insert address(id,contact,mobile,street,mbr_id) values(?,?,?,?,?)";
        Integer id = 4;
        String contact = "赵六";
        String mobile = "12345678900";
        String street = "武汉市金银湖武汉轻工大学";
        Integer mbr_id = 1;
//        Address address = runner.query(select, new BeanHandler<>(Address.class), 1);
//        System.out.println(address);

        runner.insert(insert,new BeanHandler<>(Address.class), id,contact,mobile,street,mbr_id);

    }

}
