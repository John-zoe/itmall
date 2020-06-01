package org.notail.mall.web.controller;

import org.notail.mall.common.PageBean;
import org.notail.mall.common.Result;
import org.notail.mall.pojo.Product;
import org.notail.mall.service.ProductService;
import org.notail.mall.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/product/*")
public class ProductServlet extends BaseServlet{

    ProductService service = new ProductServiceImpl();

    //load products and pagination
    //web params: categoryId, currentPage, pageSize
    public void getPage(HttpServletRequest req, HttpServletResponse resp){
        //获取到前台传过来的分类编号
        String categoryId = req.getParameter("categoryId");
        //获取前台传过来的当前页
        String page = req.getParameter("currentPage");
        //获取每页显示的记录数
        String size = req.getParameter("pageSize");

        int cateId=0;
        if(categoryId!=null){
            cateId=Integer.parseInt(categoryId);
        }

        int currenPage=1;  //如果前台没有传递当前页，则默认值为1
        if(page!=null){
            currenPage=Integer.parseInt(page);
        }

        int pageSize=10;//如果前台没有传递每页条数，则默认值为10
        if(size!=null){
            pageSize= Integer.parseInt(size);
        }

        PageBean<Product> pageBean = service.loadPage(cateId, currenPage, pageSize);

        writeJsonAsString(resp,pageBean);

    }

}
