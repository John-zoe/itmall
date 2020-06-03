package org.notail.mall.web.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.notail.mall.common.Result;
import org.notail.mall.pojo.Address;
import org.notail.mall.pojo.Member;
import org.notail.mall.service.AddressService;
import org.notail.mall.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/address/*")
public class AddressServlet extends BaseServlet {

    AddressService service = new AddressServiceImpl();

    public void getAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get session
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        Result result = null;
        if (member != null){
            int memberId = member.getId();
            List<Address> addresses = service.loadAddresses(memberId);
            result = new Result(true, addresses, "load member's addresses");
        }
        else {
            result = new Result(false);
        }

        writeJsonAsString(resp, result);

    }
    public void setAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get parameters from  form subscription data
        Map<String, String[]> map = req.getParameterMap();
        Address address = new Address();
        try {
            BeanUtils.populate(address, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //parameters don't include memberId
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");
        Integer memberId = member.getId();
        address.setMbr_id(memberId);

        service.addAddress(address);

        Result result = new Result(true, "add address");
        writeJsonAsString(resp, result);

    }
}
