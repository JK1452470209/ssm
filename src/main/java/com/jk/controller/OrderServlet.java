package com.jk.controller;



import com.jk.dao.UserDao;
import com.jk.domain.Cart;
import com.jk.domain.Order;
import com.jk.domain.OrderItem;
import com.jk.domain.User;
import com.jk.service.OrderService;
import com.jk.service.UserService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * @outhor Mr.JK
 * @create 2020-04-09  15:47
 */
@Controller
public class OrderServlet{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/orderServletsendOrders")
    public void sendOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");

        orderService.sendOrder(orderId);

        resp.sendRedirect(req.getHeader("Referer"));

    }

    @RequestMapping("/orderServletreceiveOrders")
    public void receiveOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");

        orderService.reveiveOrder(orderId);

        resp.sendRedirect(req.getHeader("Referer"));


    }

    @RequestMapping("/orderServletallOrders")
    public void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.allOrders();
        List<String> nameList = new  ArrayList<>();
        for (Order order : orders){
            String username = userService.getUsername(order.getUserId());

            nameList.add(username);
        }
        req.getSession().setAttribute("orders",orders);

        req.getSession().setAttribute("nameList",nameList);

        req.getRequestDispatcher("pages/manager/order_manager.jsp").forward(req,resp);
    }

    @RequestMapping("/orderServletorderDetails")
    public void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");

        List<OrderItem> orderItems = orderService.orderDetails(orderId);

        req.getSession().setAttribute("orderItems",orderItems);

        req.getRequestDispatcher("pages/order/detail_order.jsp").forward(req,resp);
    }




    @RequestMapping("/orderServletmyOrders")
    public void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        List<Order> orders = orderService.myOrders(Integer.parseInt(id));

        req.getSession().setAttribute("orders",orders);

        req.getRequestDispatcher("pages/order/order.jsp").forward(req,resp);

    }

    /**
     * 生产订单
     * @param req
     * @param resp
     */
    @RequestMapping("/orderServletcreateorder")
    public void createorder(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //先获取Cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //获取用户信息，获取UserId
        User loginuser = (User) req.getSession().getAttribute("user");

        if (loginuser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginuser.getId();
        //调用orderService.createOrder(Cart,Userid);,生产订单
        String orderId = orderService.createOrder(cart, userId);



        //req.setAttribute("orderId",orderId);
        //请求转发
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
