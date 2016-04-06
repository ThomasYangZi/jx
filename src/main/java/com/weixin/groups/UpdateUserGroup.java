package com.weixin.groups;

import com.weixin.AccessToken;
import com.weixin.AppIdAppSecret;
import com.weixin.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thomas on 2016/3/14.
 * 移动用户所在分组
 */
@WebServlet(
        name = "UpdateUserGroup",
        urlPatterns = "/updateusergroup"
)
public class UpdateUserGroup extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //AccessToken at = TokenThread.accessToken;
        String appId = AppIdAppSecret.appID;
        String appSecret = AppIdAppSecret.appsecret;
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        String accessToken = at.getToken();

        String openId = req.getAttribute("openId").toString();
        String groupId = req.getParameter("groupId");
        if(null != accessToken){
            int result = WeixinUtil.updateUserGroup(accessToken, groupId, openId);
            System.out.println(result);
            req.getRequestDispatcher("/WEB-INF/view/backstage/listuser.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String openId = req.getParameter("openId");
        req.setAttribute("openId",openId);
        req.getRequestDispatcher("/WEB-INF/view/backstage/updateusergroup.jsp").forward(req,resp);
    }
}
