package org.notail.mall.service.impl;

import org.notail.mall.dao.ItemDao;
import org.notail.mall.dao.OrderDao;
import org.notail.mall.dao.ProductDao;
import org.notail.mall.dao.impl.OrderDaoImpl;
import org.notail.mall.dao.impl.ProductDaoImpl;
import org.notail.mall.pojo.Item;
import org.notail.mall.pojo.Orders;
import org.notail.mall.pojo.Product;
import org.notail.mall.service.OrderService;

public class OrderServiceImpl implements OrderService {
    OrderDao dao = new OrderDaoImpl();
    ProductDao productDao = new ProductDaoImpl();
    ItemDao itemDao = new ItemDaoImpl();
    @Override
    public void addOrder(Orders order,String[] ids,String[] amounts) {
        int orderId = dao.insertOrder(order);

        //add orderItem to db
        for(int i=0; i<ids.length; i++){
            //创建订单项对象
            Item item = new Item();
            item.setOrder_id(orderId);          //设置订单id
            item.setProduct_id(Integer.parseInt(ids[i])); //设置商品id
            item.setAmount(Integer.parseInt(amounts[i])); //设置当前订单项的数量

            Product product = productDao.selectProductById(Integer.parseInt(ids[i]));
            item.setTotal_price(product.getPrice()*Integer.parseInt(amounts[i]));
            item.setPayment_price(product.getSale_price()*Integer.parseInt(amounts[i]));
            itemDao.addItem(item);
        }
    }
}
