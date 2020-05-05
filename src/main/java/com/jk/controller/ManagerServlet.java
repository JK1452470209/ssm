package com.jk.controller;



import com.jk.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @outhor Mr.JK
 * @create 2020-04-11  11:12
 */
@Controller
public class ManagerServlet{
    /**
     * 判断是否为管理员
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/ManagerServletisManager")
    public void isManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("未登录试图进入后台");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        User user1 = (User) user;

        String username = user1.getUsername();

        if ("admin".equals(username)) {
            System.out.println("是管理员");
            req.getRequestDispatcher("pages/manager/manager.jsp").forward(req, resp);
        } else {

            System.out.println("不是管理员");
            req.getRequestDispatcher("pages/manager/no_manager.jsp").forward(req, resp);

        }
    }

}



