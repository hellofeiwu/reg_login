package com.imooc.servlet;

import com.imooc.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by FWU31 on 12/13/2018.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> userList = (List<User>) this.getServletContext().getAttribute("userList");
        for(User item : userList) {
            if(item.getUsername().equals(username)) {
                if(item.getPassword().equals(password)) {
                    if("true".equals(request.getParameter("remember"))) {
                        Cookie cookie = new Cookie("username", item.getUsername());
                        cookie.setPath("/reg_login");
                        cookie.setMaxAge(60*60);
                        response.addCookie(cookie);
                    }
                    request.getSession().setAttribute("user", item);
                    response.sendRedirect(request.getContextPath() + "/success.jsp");
                    return;
                }
            }
        }
        request.setAttribute("msg", "username or password not right");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
