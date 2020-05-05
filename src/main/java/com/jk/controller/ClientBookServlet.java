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

@Controller
public class ClientBookServlet{

    @Autowired
    private BookService bookService;

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/clientBookServletpage")
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("/book/clientBookServletpage?action=1");


        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }



    @RequestMapping("/clientBookServletpageByPrice")
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("/book/clientBookServletpageByPrice?action=1");


        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }


        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }


        page.setUrl(sb.toString());


        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }


}
