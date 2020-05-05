package com.jk.test;

import com.jk.dao.BookDao;
import com.jk.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;



/**
 * @outhor Mr.JK
 * @create 2020-04-27  16:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testAddBook(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ac.getBean("bookDao");
        Book book = new Book(null,"测试","坤",new BigDecimal(99),1,1,null);
        bookDao.addBook(book);
    }

    @Test
    public void testdeleteBookById(){
        bookDao.deleteBookById(62);
    }

    @Test
    public void testUpdateBook(){
        Book book = new Book(61,"测试","坤",new BigDecimal(99),1,1,null);

        bookDao.updateBook(book);
    }

    @Test
    public void testQueryBookById(){
        Book book = bookDao.queryBookById(2);
        System.out.println(book);
    }

    @Test
    public void testQueryBooks(){
        List<Book> books = bookDao.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }

    @Test
    public void testQueryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void testQueryForPageItems(){
        System.out.println(bookDao.queryForPageItems(1, 4));
    }

    @Test
    public void testQueryForPageTotalCountByPrice(){
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 100));
    }

    @Test
    public void testQueryForPageItemsByPrice(){
        System.out.println(bookDao.queryForPageItemsByPrice(0, 4, 10, 100));
    }
}