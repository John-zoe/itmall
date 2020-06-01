package org.notail.mall.web.controller;

import org.notail.mall.common.Result;
import org.notail.mall.pojo.Category;
import org.notail.mall.pojo.Product;
import org.notail.mall.service.CategoryService;
import org.notail.mall.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    CategoryService service = new CategoryServiceImpl();


    //get navigation to top.html
    public List<Category> getNav(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
        List<Category> categories = service.findAll();
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("查询成功");
        result.setData(categories);

        writeJsonAsString(resp,result);

        return categories;

    }


}
