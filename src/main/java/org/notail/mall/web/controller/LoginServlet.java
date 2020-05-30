package org.notail.mall.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.notail.mall.common.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应格式
        resp.setContentType("application/json");

        //获取写入对象
        PrintWriter writer = resp.getWriter();

        //1.接受用户传过来的手机号和密码
        String mobile = (String) req.getParameter("mobile");
        String pwd = (String) req.getParameter("pwd");

        ObjectMapper mapper = new ObjectMapper();
        //2.判断手机号和密码
        if ("18888888888".equals(mobile) && "123456".equals(pwd)){
            Result result = new Result(true, null, "login success!!");
            String suc = mapper.writeValueAsString(result);
            writer.print(suc);
        }
        else{
            Result result = new Result(false, null, "login failed!!");
            String fail = mapper.writeValueAsString(result);
            writer.print(fail);
        }
    }
}
