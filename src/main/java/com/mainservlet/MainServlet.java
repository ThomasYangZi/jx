package com.mainservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/12.
 *
 */
@WebServlet(
        name = "MainServlet",
        urlPatterns = "/main",
        loadOnStartup = 1
)
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action==null|| action.equals("list")){
            req.getRequestDispatcher("/WEB-INF/view/backstage/add.jsp");
        }else if(action.equals("listjx")){
            resp.sendRedirect("/listnews");
        }else if(action.equals("listtrainer")){
            resp.sendRedirect("/listtrainer");
        }else if(action.equals("listcomplain")){
            resp.sendRedirect("/listcomplain");     //显示投诉
        }else if(action.equals("listgroup")){
            resp.sendRedirect("/listgroup");          //进度查询
        }else if(action.equals("listuser")){
            resp.sendRedirect("/listuser");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
