package com.jk.test;

import com.jk.dao.UserDao;
import com.jk.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  17:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testQueryUserByUsername() {

        if(userDao.queryUserByUsername("admin") == null){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void testQueryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void testSaveUser() throws IOException {

        System.out.println(userDao.saveUser(new User(null,"lin101","123456","lin@qq.com")));


    }
}
