package com.jx.action.user;

import com.jx.bean.UserBean;
import com.jx.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 2016/2/23.
 *
 */
@WebServlet(
        name = "ListUser",
        urlPatterns = "/listuser"
)
public class ListUser extends HttpServlet {
    UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBean> list;
        int usernum = Integer.parseInt(req.getParameter("usernum"));
        int jxid = Integer.parseInt(req.getParameter("jxid"));
        list = userDao.listUser(usernum, jxid);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/view/backstage/listuser.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBean> list;
        list = userDao.getAllUser();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/view/backstage/listuser.jsp").forward(req,resp);
    }
}
