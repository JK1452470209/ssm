package com.jk.service.impl;

import com.jk.dao.BookDao;
import com.jk.dao.OrderDao;
import com.jk.dao.OrderItemDao;
import com.jk.domain.*;
import com.jk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;

        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);


        orderDao.saveOrder(order);


        for (Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){

            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);

            orderItemDao.saveOrderItem(orderItem);


            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> myOrders(Integer userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public List<OrderItem> orderDetails(String orderId) {
        return orderItemDao.queryOrderDetailByOrderId(orderId);

    }

    @Override
    public List<Order> allOrders() {
        return orderDao.allOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changOrderStatus(orderId);
    }

    @Override
    public void reveiveOrder(String orderId) {
        orderDao.changOrderStatus(orderId);
    }
}
