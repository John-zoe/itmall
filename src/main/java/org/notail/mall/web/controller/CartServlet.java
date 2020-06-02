package org.notail.mall.web.controller;

import org.notail.mall.common.Result;
import org.notail.mall.pojo.CartItem;
import org.notail.mall.pojo.Product;
import org.notail.mall.service.ProductService;
import org.notail.mall.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {
    ProductService service = new ProductServiceImpl();

    public void setSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int productId = Integer.parseInt(req.getParameter("productId"));
        int num = Integer.parseInt(req.getParameter("number"));

        Product product = service.loadDetail(productId);

        CartItem cartItem = new CartItem(product, num);

        //get session
        HttpSession session = req.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null){
            cart = new ArrayList<>();
        }
        cart.add(cartItem);
        //update session
        session.setAttribute("cart", cart);

        Result result = new Result(true, cart, "add product into cart");

        writeJsonAsString(resp, result);
    }
    public void getCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        Result result;
        if (cart == null){
            result = new Result(false, "cart has nothing");
        }
        else {
            result = new Result(true, cart, "show cart");
        }
        writeJsonAsString(resp, result);
    }

}
