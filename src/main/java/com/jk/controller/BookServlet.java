package com.jk.controller;



import com.jk.domain.Book;
import com.jk.domain.Page;
import com.jk.service.BookService;
import com.jk.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class BookServlet{

    @Autowired
    private BookService bookService;


    @RequestMapping("/bookServletpage")
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);


        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("bookServletpage?action=1");

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    @RequestMapping("/bookServletadd")
    public void add(HttpServletRequest req, HttpServletResponse resp,Book book) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;

        bookService.addBook(book);


        resp.sendRedirect(req.getContextPath() + "/bookServletpage?pageNo=" + pageNo);

    }

    @RequestMapping("/bookServletdelete")
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);


        bookService.deleteBookById(id);


        resp.sendRedirect(req.getContextPath() + "/bookServletpage?pageNo=" + req.getParameter("pageNo"));

    }

    @RequestMapping("/bookServletupdate")
    public void update(HttpServletRequest req, HttpServletResponse resp,Book book) throws ServletException, IOException {


        bookService.updateBook(book);

        resp.sendRedirect(req.getContextPath() + "/bookServletpage?pageNo=" + req.getParameter("pageNo"));

    }


    @RequestMapping("/bookServletgetBook")
    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookService.queryBooks();

        req.setAttribute("books",books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

}
