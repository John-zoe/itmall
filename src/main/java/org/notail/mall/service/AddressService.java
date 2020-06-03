package org.notail.mall.service;

import org.notail.mall.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> loadAddresses(int memberId);

    void addAddress(Address address);

    Address loadAddressById(int addressId);
}
