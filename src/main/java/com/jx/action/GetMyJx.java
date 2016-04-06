package com.jx.action;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by Thomas on 2016/2/25.
 * 个人中心页获取已报名的驾校
 */
@WebServlet(
        name = "GetMyJx",
        urlPatterns = "/getmyjx"
)
public class GetMyJx extends HttpServlet{

}
