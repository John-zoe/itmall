package org.notail.mall.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.notail.mall.common.Result;
import org.notail.mall.pojo.Member;
import org.notail.mall.service.MemberService;
import org.notail.mall.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {
    MemberService memberService = new MemberServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应格式
        resp.setContentType("application/json");

        //获取写入对象
        PrintWriter writer = resp.getWriter();

        //1.接受用户传过来的手机号和密码
        String mobile = (String) req.getParameter("mobile");
        String pwd = (String) req.getParameter("pwd");

        Result result = null;
        ObjectMapper mapper = new ObjectMapper();
        //2.判断手机号和密码
        Member member = memberService.login(mobile, pwd);
        if (member != null){
            result = new Result(true, null,"登陆成功");
            //保存到session
            HttpSession session = req.getSession();
            session.setAttribute("member", member);
        }
        else{
            result = new Result(false, null,"手机号或密码错误");
        }
        String s = mapper.writeValueAsString(result);
        writer.print(s);
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应格式
        resp.setContentType("application/json");

        //获取写入对象
        PrintWriter writer = resp.getWriter();

        Result result = null;
        ObjectMapper mapper = new ObjectMapper();

        Member member = new Member();

        Map<String, String[]> map = req.getParameterMap();

        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean success = memberService.register(member);
        if (success){
            result = new Result(true, "注册成功");
        }
        else{
            result = new Result(false, "手机号已被注册");
        }
        String s = mapper.writeValueAsString(result);
        writer.print(s);
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        HttpSession session = req.getSession();
        session.invalidate();

        Result result = new Result();
        result.setFlag(true);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(result);

        resp.getWriter().print(s);
    }

    public void getNickName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();  //无则创建，有则获取
        Member member = (Member)session.getAttribute("member");   //如果已经登陆过member对象不为null,否则为null

        ObjectMapper mapper = new ObjectMapper();
        //返回的结果对象
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("");
        result.setData(member);

        //转换成json
        String s = mapper.writeValueAsString(result);

        resp.getWriter().print(s);
    }
}
