package org.notail.mall.dao;

import org.notail.mall.pojo.Address;

import java.util.List;

public interface AddressDao {
    List<Address> selectAddresses(int memberId);

    void updateDefaultAddress(Integer memberId);

    void insertAddress(Address address);

    Address selectAddress(int addressId);
}
