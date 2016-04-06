package com.jx.action.user;

import com.jx.bean.UserBean;
import com.jx.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Thomas on 2016/2/15.
 * 获取用户信息
 */
@WebServlet(
        name = "GetUser",
        urlPatterns = "/getuser"
)
public class GetUser extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("openid")){
            String openid = req.getParameter("openid");
            UserBean userBean=userDao.getByopenid(openid);
            req.setAttribute("userBean",userBean);
        }else {
            UserBean userBean = new UserBean();
            userBean.setUsername("请查询");
            userBean.setUserphone("请查询");
            userBean.setUserdate(new Date());
            userBean.setJxid(1000);
            req.setAttribute("userBean",userBean);
        }
        req.getRequestDispatcher("/WEB-INF/view/backstage/showuser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userphone = req.getParameter("userphone");
        UserBean userBean = userDao.getByUserphone(userphone);
        req.setAttribute("userBean",userBean);
        req.getRequestDispatcher("/WEB-INF/view/backstage/showuser.jsp").forward(req,resp);
    }
}
