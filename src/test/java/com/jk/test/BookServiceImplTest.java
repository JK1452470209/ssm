package com.jk.test;

import com.jk.domain.Book;
import com.jk.domain.Page;
import com.jk.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @outhor Mr.JK
 * @create 2020-04-27  20:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "帅哥", "坤哥", new BigDecimal(999), 1, 1, null));

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(61);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(63, "帅哥秘籍", "坤哥", new BigDecimal(999), 1, 1, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(63));
    }

    @Test
    public void queryBooks() {
        for (Book querybook : bookService.queryBooks()) {
            System.out.println(querybook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1,4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}