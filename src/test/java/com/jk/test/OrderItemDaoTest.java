package com.jk.test;

import com.jk.dao.OrderItemDao;
import com.jk.domain.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;


/**
 * @outhor Mr.JK
 * @create 2020-04-27  18:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class OrderItemDaoTest {

    @Autowired
    OrderItemDao orderItemDao;

    @Test
    public void testSaveOrderItem(){
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"15864227788961"));
        }
    @Test
    public void testQueryOrderDetailByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailByOrderId("158812304388135");
        System.out.println(orderItems);

    }



}
