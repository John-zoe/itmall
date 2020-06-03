package org.notail.mall.service.impl;

import org.notail.mall.dao.AddressDao;
import org.notail.mall.pojo.Address;
import org.notail.mall.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    
    AddressDao dao = new AddressDaoImpl();
    
    @Override
    public List<Address> loadAddresses(int memberId) {
        List<Address> addresses = dao.selectAddresses(memberId);
        return addresses;
    }

    @Override
    public void addAddress(Address address) {
        //judge address is default address
        if (address.getDefault_value() == 1){
            //if false, set previous default address 0(false) and new address 1(true)
            Integer memberId = address.getMbr_id();
            dao.updateDefaultAddress(memberId);
        }
        //if true, insert
        //anyway , new address will be inserted
        dao.insertAddress(address);
    }

    @Override
    public Address loadAddressById(int addressId) {
        Address address = dao.selectAddress(addressId);
        return address;
    }
}
