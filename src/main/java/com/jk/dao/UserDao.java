package com.jk.dao;


import com.jk.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface UserDao {

    @Select("select `id`,`username`,`password`,`email` from t_user where username = #{username}")
    User queryUserByUsername(String username);

    @Select("select `id`,`username`,`password`,`email` from t_user where username = #{arg0} and password = #{arg1}")
    User queryUserByUsernameAndPassword(String username, String password);

    @Insert("insert into t_user(`username`,`password`,`email`) values('${username}','${password}','${email}')")
    int saveUser(User user);

    @Select("select username from t_user where id = #{userId}")
    String getUsername(Integer userId);


}
