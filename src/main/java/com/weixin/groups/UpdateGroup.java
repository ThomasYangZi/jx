package com.weixin.groups;

import com.weixin.AccessToken;
import com.weixin.TokenThread;
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
        name = "UpdateGroup",
        urlPatterns = "/updategroup"
)
public class UpdateGroup extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int groupId = Integer.parseInt(req.getParameter("groupid"));

        AccessToken at = TokenThread.accessToken;
        String accessToken = at.getToken();

        if (action.equals("delete")){
            int result = WeixinUtil.deleteGroup(accessToken, groupId);
            if (result == 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除分组错误代码"+result);
            }
        }

        req.getRequestDispatcher("/listgroup").forward(req,resp);
    }
}
