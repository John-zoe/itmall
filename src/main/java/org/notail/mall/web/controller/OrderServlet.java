package org.notail.mall.web.controller;


import org.notail.mall.common.Result;
import org.notail.mall.pojo.*;
import org.notail.mall.service.OrderService;
import org.notail.mall.service.ProductService;
import org.notail.mall.service.impl.AddressServiceImpl;
import org.notail.mall.service.impl.OrderServiceImpl;
import org.notail.mall.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    ProductService productService = new ProductServiceImpl();
    AddressServiceImpl addressService = new AddressServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    public void getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ids = req.getParameter("ids");
        String nums = req.getParameter("amounts");

        String[] idArr = ids.split(",");
        String[] numArr = nums.split(",");

        List<CartItem> list = new ArrayList<>();

        for (int i = 0; i < idArr.length; i++){
            int id = Integer.parseInt(idArr[i]);
            int num = Integer.parseInt(numArr[i]);
            Product product = productService.loadDetail(id);
            CartItem cartItem = new CartItem(product, num);
            list.add(cartItem);
        }

        Result result = new Result(true, list, "load cartItems");
        writeJsonAsString(resp, result);
    }

    public void submitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ajax pass parameters from form(<input/>); session
        //ids, amounts, remark,address_id, member(from session)

        //getParameterValues() for getting array
        String[] ids = req.getParameterValues("ids");
        String[] amounts = req.getParameterValues("amounts");

        String addressId = req.getParameter("address_id");
        String remark = req.getParameter("remark");

        HttpSession session = req.getSession();

        //set orderNumber
        String orderNumber = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        orderNumber = sdf.format(new Date());

        //set address
        Address address =  addressService.loadAddressById(Integer.parseInt(addressId));

        //set product
        Product product = null;

        //set member
        Member member = (Member) session.getAttribute("member");

        //set price and amount
        Double totalPayPrice = 0d;
        Double totalPrice = 0d;
        Integer totalAmout = 0;

        for (int i = 0; i < ids.length; i++){
            product = productService.loadDetail(Integer.parseInt(ids[i]));
            Integer amount = Integer.parseInt( amounts[i]);
            totalAmout += amount;
            totalPrice += product.getPrice() * amount;
            totalPayPrice += product.getSale_price() * amount;
        }


        Orders orders = new Orders();
        orders.setStatus(0);
        orders.setNumber(orderNumber);
        orders.setConcat(address.getContact());
        orders.setZipcode(address.getZipcode());
        orders.setStreet(address.getStreet());
        orders.setMobile(address.getMobile());
        orders.setBuyer_id(member.getId());
        orders.setRemark(remark);
        orders.setCreate_time(new Date());
        orders.setPayment_price(totalPayPrice);
        orders.setTotal_price(totalPrice);
        orders.setTotal_amount(totalAmout);

        //add order to db
        orderService.addOrder(orders, ids, amounts);

    }

}
