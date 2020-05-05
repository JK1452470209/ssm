package com.jk.test;

import com.jk.dao.OrderDao;
import com.jk.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  18:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void testSaveOrder() {
        orderDao.saveOrder(new Order("12345678910",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void testQueryMyOrders(){
        List<Order> orders = orderDao.queryMyOrders(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void testAllOrders() {
        List<Order> orders = orderDao.allOrders();
        System.out.println(orders);
    }

    @Test
    public void testChangOrderStatus() {
        orderDao.changOrderStatus("15866037542541");
    }
}

