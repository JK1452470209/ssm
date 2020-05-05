package com.jk.controller;

import com.google.gson.Gson;
import com.jk.domain.Book;
import com.jk.domain.Cart;
import com.jk.domain.CartItem;
import com.jk.service.BookService;
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


/**
 * @outhor Mr.JK
 * @create 2020-04-08  20:49
 */
@Controller
public class CartServlet{

    @Autowired
    private BookService bookService;

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/cartServletupdateCount")
    public void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),0);
        //获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null){
            cart.updateCount(id,count);
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }



    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/cartServletclear")
    public void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //清空购物车
            cart.clear();
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    @RequestMapping("/cartServletdeleteItem")
    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if (cart != null){

            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }



    }

    @RequestMapping("/cartServletaddItem")
    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id = WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));

        req.getSession().setAttribute("lastName",cartItem.getName());


        resp.sendRedirect(req.getHeader("Referer"));


    }

    @RequestMapping("/cartServletajaxAddItem")
    public void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("加入购物车");
        System.out.println("商品编号：" + req.getParameter("id"));

        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);



        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));


        req.getSession().setAttribute("lastName",cartItem.getName());

       Map<String,Object> resultMap = new HashMap<>();

       resultMap.put("totalCount",cart.getTotalCount());
       resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);


    }
}
