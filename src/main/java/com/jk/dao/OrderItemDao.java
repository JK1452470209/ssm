package com.jk.dao;


import com.jk.domain.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemDao {

    @Insert("insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values('${name}',${count},${price},${totalPrice},${orderId})")
    int saveOrderItem(OrderItem orderItem);

    @Select("SELECT `name` , `count` , `price` , `total_price` as totalPrice, `order_id` as orderId  FROM t_order_item WHERE order_id = #{orderId}")
    List<OrderItem> queryOrderDetailByOrderId(String orderId);


}
