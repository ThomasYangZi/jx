package com.jx.action;

import com.jx.bean.UserBean;
import com.jx.dao.UserDao;
import com.weixin.OAuth2.SNSUserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/25.
 * 个人中心页获取我的进度
 */
@WebServlet(
        name = "GetProceeding",
        urlPatterns = "/getproceeding"
)
public class GetProceeding extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session
        HttpSession session = req.getSession();
        SNSUserInfo snsUserInfo = (SNSUserInfo)session.getAttribute("snsUserInfo");
        //获取用户openId
        String openId = snsUserInfo.getOpenId();
        UserBean userBean = userDao.getByopenid(openId);
        req.setAttribute("userBean",userBean);
        req.getRequestDispatcher("/WEB-INF/view/jsp/personalproceeding.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用户签到

    }
}
