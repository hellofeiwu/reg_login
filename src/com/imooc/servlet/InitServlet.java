package com.imooc.servlet;

import com.imooc.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

/**
 * user init servlet
 */
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("in init servlet");
        List<User> userList = new ArrayList<User>();
        this.getServletContext().setAttribute("userList", userList);
    }
}
