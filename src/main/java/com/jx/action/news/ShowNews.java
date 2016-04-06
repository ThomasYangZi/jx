package com.jx.action.news;

import com.jx.bean.NewBean;
import com.jx.dao.NewsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/11.
 *
 */
@WebServlet(
        name = "ShowNews",
        urlPatterns = "/shownews"
)
public class ShowNews extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsDao newsDao = new NewsDao();
        int jxid = Integer.parseInt(req.getParameter("jxid"));

        NewBean newBean = newsDao.getById(jxid);
        req.setAttribute("jxid",jxid);
        req.setAttribute("newBean",newBean);
        req.getRequestDispatcher("xiangqingye_content.jsp").forward(req,resp);
    }
}
