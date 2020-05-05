package com.jk.dao;


import com.jk.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDao {


    @Insert("insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values('${orderId}',now(),${price},${status},${userId})")
    public int saveOrder(Order order);

    @Select("SELECT `order_id` as orderId , `create_time` as createTime , `price` , `status` , `user_id` as userId  FROM t_order WHERE user_id = #{userId}")
    public List<Order> queryMyOrders(Integer userId);

    @Select("SELECT `order_id` as orderId , `create_time` as createTime , `price` , `status` , `user_id` as userId  FROM t_order")
    public List<Order> allOrders();

    @Update("update t_order SET status=(CASE WHEN status = 0 THEN  1 WHEN status = 1 THEN  2 END) WHERE order_id =#{orderId}")
    public void changOrderStatus(String orderId);
}
