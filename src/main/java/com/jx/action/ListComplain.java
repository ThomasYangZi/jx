package com.jx.action;

import com.jx.dao.ComplainDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 2016/2/15.
 *
 */
@WebServlet(
        name = "ListComplain",
        urlPatterns = "/listcomplain"
)
public class ListComplain extends HttpServlet {
    ComplainDao complainDao = new ComplainDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list = complainDao.showComplain();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/view/backstage/listcomplain.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
