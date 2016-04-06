package com.jx.action.news;

import com.jx.bean.NewBean;
import com.jx.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 2016/2/11.
 *
 */
@WebServlet(
        name = "ViewNews",
        urlPatterns = "/viewnews"
)
public class ViewNews extends HttpServlet {
    NewsDao newsDao = new NewsDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewBean> list;
        list=newsDao.listNews();
        req.setAttribute("list",list);
        req.getRequestDispatcher("jiaxiaoliebiao.jsp").forward(req,resp);
    }
}
