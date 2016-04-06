package com.jx.action.user;

import com.jx.bean.UserBean;
import com.jx.dao.NewsDao;
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
 * Created by Thomas on 2016/2/23.
 * 报名
 */
@WebServlet(
        name = "AddUser",
        urlPatterns = "/adduser"
)
public class AddUser extends HttpServlet {
    UserBean userBean = new UserBean();
    UserDao userDao = new UserDao();
    NewsDao newsDao = new NewsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("yuyuebiaodan.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        SNSUserInfo snsUserInfo = (SNSUserInfo)session.getAttribute("snsUserInfo");
        String openId = snsUserInfo.getOpenId();
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        String jxname = null;
        if(req.getParameter("jxname")!=null) {
             jxname = req.getParameter("jxname");
        }
        int jxid = 1;
        if(jxname==null){
            jxid = Integer.parseInt(req.getParameter("jxid")); //驾校报名页隐藏参数
        }else{
            jxid = newsDao.getJxid(jxname);
            if(jxid==1) {
                req.setAttribute("errorMsg", "你输入的驾校不存在，请重新输入报名信息！");
                req.getRequestDispatcher("yuyuebiaodan.jsp").forward(req,resp);
            }
        }
        userBean.setOpenid(openId);
        userBean.setUsername(username);
        userBean.setUserphone(userphone);
        userBean.setJxid(jxid);

        userDao.addUser(userBean);

        req.getRequestDispatcher("/WEB-INF/view/jsp/registersuccess.jsp");
    }
}
