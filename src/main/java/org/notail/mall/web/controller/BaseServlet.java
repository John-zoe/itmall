package org.notail.mall.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get function name in url
        String methodName = new String();
        StringBuffer requestURL = req.getRequestURL();
        methodName = requestURL.substring(requestURL.lastIndexOf("/") + 1);

        //get subclass via reflection
        Class<? extends BaseServlet> subclass = this.getClass();

        try {
            Method method = subclass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void writeJsonAsString(HttpServletResponse resp, Object object){
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            resp.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
