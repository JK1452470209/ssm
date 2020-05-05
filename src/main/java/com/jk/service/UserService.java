package com.jk.service;


import com.jk.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {

     void registUser(User user);

     User login(User user);

     boolean existUsername(String username);

     String getUsername(Integer userId);

}
