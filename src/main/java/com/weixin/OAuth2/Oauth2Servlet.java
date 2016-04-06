package com.weixin.OAuth2;

import com.jx.bean.UserBean;
import com.jx.dao.NewsDao;
import com.jx.dao.UserDao;
import com.weixin.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/4.
 *
 */
@WebServlet(
        name = "Oauth2Servlet",
        urlPatterns = "/oauth"
)
public class Oauth2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        System.out.println(code);
        //获取返回状态码
        String action = request.getParameter("state");
        System.out.println(action);
        String appId = getServletContext().getInitParameter("appid");
        System.out.println(appId);
        String appSecret = getServletContext().getInitParameter("appsecret");
        System.out.println(appSecret);

        // 获取网页授权access_token

        WeixinOauth2Token weixinOauth2Token = WeixinUtil.getOauth2AccessToken(appId,appSecret,code);
        // 网页授权接口访问凭证
        String accessToken = weixinOauth2Token.getAccessToken();
        // 用户标识
        String openId = weixinOauth2Token.getOpenId();
        // 获取用户信息
        SNSUserInfo snsUserInfo = WeixinUtil.getSNSUserInfo(accessToken, openId);

        session.setAttribute("snsUserInfo",snsUserInfo);

        // 设置要传递的参数
        //request.setAttribute("snsUserInfo", snsUserInfo);

        //获取请求url，然后重定向回上一页面，并把snsUserInfo传出去

        /*String url = OAuth2Filter.url;
        RequestDispatcher requestDispatcher=request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
        */
        if(action.equals("index")) {
            // 跳转到index.jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(action.equals("personalproceeding")){
            request.getRequestDispatcher("/getproceeding").forward(request,response);
        }else{
            UserDao userDao = new UserDao();
            UserBean userBean  = userDao.getByopenid(snsUserInfo.getOpenId());
            NewsDao newsDao = new NewsDao();
            String jxname = newsDao.getJxName(userBean.getJxid());
            request.setAttribute("jxname",jxname);
            request.getRequestDispatcher("gerenzhongxin.jsp").forward(request,response);
        }
    }
}
