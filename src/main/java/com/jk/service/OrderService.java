package com.jk.service;

import com.jk.domain.Cart;
import com.jk.domain.Order;
import com.jk.domain.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> myOrders(Integer userId);

    public List<OrderItem> orderDetails(String orderId);

    public List<Order> allOrders();

    public void sendOrder(String orderId);

    public void reveiveOrder(String orderId);



}
