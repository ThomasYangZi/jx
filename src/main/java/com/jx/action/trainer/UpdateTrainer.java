package com.jx.action.trainer;

import com.jx.dao.TrainerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/20.
 *
 */
@WebServlet(
        name = "UpdateTrainer",
        urlPatterns = "/updatetrainer"
)
public class UpdateTrainer extends HttpServlet {
    TrainerDao trainerDao = new TrainerDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String trainerId = req.getParameter("trainerid");
        if (action.equals("delete")){
            int trainerid = Integer.parseInt(trainerId);
            trainerDao.deleteTrainer(trainerid);
            resp.sendRedirect("/listtrainer");
        }
    }
}
