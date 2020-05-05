package com.jk.test;

import com.jk.domain.Cart;
import com.jk.domain.CartItem;
import com.jk.domain.Order;
import com.jk.domain.OrderItem;
import com.jk.service.OrderService;
import com.jk.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  20:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(2,"数据结构",2,new BigDecimal(100),new BigDecimal(200)));


        System.out.println("订单号为：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void myOrders() {
        List<Order> orders = orderService.myOrders(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void orderDetails() {
        List<OrderItem> orderItems = orderService.orderDetails("15864228412151");
        System.out.println(orderItems);
    }

    @Test
    public void allOrders() {
        List<Order> orders = orderService.allOrders();
        System.out.println(orders);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("15866037542541");

    }

    @Test
    public void reveiveOrder() {
        orderService.reveiveOrder("15866037542541");

    }
}