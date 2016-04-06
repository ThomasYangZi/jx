package com.weixin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by Thomas on 2016/1/31.
 *
 */
@WebServlet(
        name = "InitServlet",
        urlPatterns = "/init",
        loadOnStartup = 1
)
public class InitServlet extends HttpServlet {
    public void init() throws ServletException{
        TokenThread.appid = getServletContext().getInitParameter("appid");
        TokenThread.appsecret = getServletContext().getInitParameter("appsecret");
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            System.out.print("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}
