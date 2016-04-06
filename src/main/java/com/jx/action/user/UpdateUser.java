package com.jx.action.user;

import com.jx.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新用户
 * Created by Thomas on 2016/3/14.
 *
 */
@WebServlet(
        name = "UpdateUser",
        urlPatterns = "/updateuser"
)
public class UpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String action = req.getParameter("action");
        if (action.equals("delete")){
            String openId = req.getParameter("openId");
            userDao.deleteUser(openId);
            resp.sendRedirect("/listuser");
        }
    }
}
