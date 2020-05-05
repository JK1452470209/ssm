package com.jk.controller;

import com.google.gson.Gson;
import com.jk.domain.User;
import com.jk.service.UserService;
import com.jk.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
public class UserServlet{

    @Autowired
    private UserService userService;

    @RequestMapping("/userServletajaxExistsUsername")
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数username
        String username = req.getParameter("username");
        //调用userService.existUsername
        boolean existUsername = userService.existUsername(username);
        //把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }


    @RequestMapping("/userServletlogin")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User loginUser = userService.login(new User(null, username, password, null));

        if (loginUser == null){

            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {

            System.out.println("登录成功");

            req.getSession().setAttribute("user",loginUser);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    @RequestMapping("/userServletregist")
    public void regist(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        req.getSession().setAttribute("user",user);

        if (token != null && token.equalsIgnoreCase(code)){


            if (userService.existUsername(username)){
                //不可用
                System.out.println("用户名[" + username + "]已存在！");

                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);


                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);

            }else{

                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);

            }

        }else {

            req.setAttribute("msg","验证码错误!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            System.out.println("验证码[" + code +"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }


    @RequestMapping("/userServletlogout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }


}
