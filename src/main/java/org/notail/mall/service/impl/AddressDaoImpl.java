package org.notail.mall.service.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.notail.mall.dao.AddressDao;
import org.notail.mall.pojo.Address;
import org.notail.mall.utils.JDBCUtil;

import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Address> selectAddresses(int memberId) {
        List<Address> addresses = null;
        String sql = "select * from address where mbr_id = ?";
        try {
            addresses = runner.query(sql, new BeanListHandler<>(Address.class), memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public void updateDefaultAddress(Integer memberId) {
        String sql = "update address set default_value = 0 where mbr_id = ?";
        try {
            runner.update(sql, memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAddress(Address address) {
        String sql = "insert into address values(null,?,?,?,?,?,?)";
        try {
            runner.update(sql, address.getContact(), address.getMobile(), address.getStreet(), address.getZipcode(), address.getMbr_id(), address.getDefault_value());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address selectAddress(int addressId) {
        String sql = "select * from address where id = ?";
        Address address = null;
        try {
             address = runner.query(sql, new BeanHandler<>(Address.class), addressId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

}
