package com.jk.service.impl;


import com.jk.dao.UserDao;
import com.jk.domain.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Override
    public void registUser(User user) {
            userdao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userdao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {

        if (userdao.queryUserByUsername(username) == null){
            //等于null，说明没查到，表示可用
            return false;
        }
        return true;
    }

    @Override
    public String getUsername(Integer userId) {
        return userdao.getUsername(userId);
    }

}
