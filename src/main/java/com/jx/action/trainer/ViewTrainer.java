package com.jx.action.trainer;

import com.jx.bean.TrainerBean;
import com.jx.dao.TrainerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 2016/2/20.
 * 前端显示教练
 */
@WebServlet(
        name = "ViewTrainer",
        urlPatterns = "/viewtrainer"
)
public class ViewTrainer extends HttpServlet{
    TrainerDao trainerDao = new TrainerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //nullPoint
        if(req.getParameter("action").equals("view")){
            int jxid = Integer.parseInt(req.getParameter("jxid"));
            List<TrainerBean> list;
            list = trainerDao.listTrainerByJxid(jxid);
            req.setAttribute("list",list);
            req.getRequestDispatcher("jiaolianliebiao.jsp").forward(req,resp);
        }else {
            this.doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TrainerBean> list;
        list = trainerDao.listTrainer();
        req.setAttribute("list",list);
        req.getRequestDispatcher("jiaolianliebiao.jsp").forward(req,resp);
    }
}
