package com.weixin.groups;

import com.weixin.AccessToken;
import com.weixin.AppIdAppSecret;
import com.weixin.WeixinUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/24.
 *
 */
@WebServlet(
        name = "ListGroup",
        urlPatterns = "/listgroup"
)
public class ListGroup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //AccessToken at = TokenThread.accessToken;
        String appId = AppIdAppSecret.appID;
        String appSecret = AppIdAppSecret.appsecret;
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
        String accessToken = at.getToken();
        JSONObject jsonObject = WeixinUtil.getAllGroup(accessToken);
        JSONArray jsonArray = new JSONArray(jsonObject.get("groups").toString());

        List<Group> list = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            Group group = new Group();
            group.setGroupid(jsonArray.getJSONObject(i).getInt("id"));
            group.setGroupname(jsonArray.getJSONObject(i).getString("name"));
            group.setCount(jsonArray.getJSONObject(i).getInt("count"));
            list.add(group);
        }
        if (list == null){
            Group group = new Group();
            group.setGroupid(10000);
            group.setGroupname("还没有分组");
            group.setCount(0);
            list.add(group);
        }

        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/view/backstage/listgroup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
