package com.jk.test;

import com.jk.domain.User;
import com.jk.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  20:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void registUser() {
        userService.registUser(new User(null,"lin102","123456","123@qq.com"));

    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"lin","123456","1")));

    }

    @Test
    public void existUsername() {
        if (userService.existUsername("lin")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用!");
        }
    }
}