package com.jk.dao;

import com.jk.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface BookDao {

    @Insert("insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`)values('${name}','${author}',${price},${sales},${stock},'${imgPath}')")
    int addBook(Book book);

    @Delete("delete from t_book where id = #{id}")
    int deleteBookById(Integer id);

    @Update("update t_book set `name`='${name}',`author`='${author}',`price`=${price},`sales`=${sales},`stock`=${stock},`img_path`='${imgPath}' where id = ${id}")
    int updateBook(Book book);

    @Select("select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` from t_book where id = #{id}")
    Book queryBookById(Integer id);

    @Select("select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` from t_book")
    List<Book> queryBooks();

    @Select("select count(*) from t_book")
    Integer queryForPageTotalCount();

    @Select("select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` from t_book limit ${arg0},${arg1}")
    List<Book> queryForPageItems(int begin, int pageSize);

    @Select("select count(*) from t_book where price between #{arg0} and #{arg1}")
    Integer queryForPageTotalCountByPrice(int min, int max);

    @Select("select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
            "from t_book where price between #{arg2} and #{arg3} order by price limit #{arg0},#{arg1}")
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
