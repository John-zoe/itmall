package org.notail.mall.service;

import org.notail.mall.pojo.Orders;

public interface OrderService {
    void addOrder(Orders order,String[] ids,String[] amounts);
}
