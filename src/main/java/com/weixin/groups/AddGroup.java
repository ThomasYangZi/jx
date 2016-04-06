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
 * Created by Thomas on 2016/3/2.
 *
 */
@WebServlet(
        name = "AddGroup",
        urlPatterns = "/addgroup"
)
public class AddGroup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //AccessToken at = TokenThread.accessToken;
        String appId = AppIdAppSecret.appID;
        String appSecret = AppIdAppSecret.appsecret;
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        String accessToken = at.getToken();


        String groupName = req.getParameter("groupname");

        if (null != accessToken) {
            int result = WeixinUtil.createGroup(groupName, accessToken);
            System.out.println(result);
            req.getRequestDispatcher("/listgroup").forward(req,resp);
        }
    }
}
