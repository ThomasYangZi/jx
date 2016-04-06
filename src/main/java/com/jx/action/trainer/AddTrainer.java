package com.jx.action.trainer;

import com.jx.bean.TrainerBean;
import com.jx.dao.TrainerDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 2016/2/20.
 *
 */
@WebServlet(
        name = "AddTrainer",
        urlPatterns = "/addtrainer"
)
public class AddTrainer extends HttpServlet{
    TrainerDao trainerDao = new TrainerDao();
    TrainerBean trainerBean = new TrainerBean();

    private String uploadPath = "resources/image/jxtrainer/";     //教练头像目录
    private String tempPath = "resources/image/jxtrainertmp/";       //临时文件目录
    private String serverPath = null;
    private String[] fileType = new String[]{".jpg",".png",".jpeg"};
    private int sizeMax = 5;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serverPath = getServletContext().getRealPath("/").replace("\\","/");

        if(!new File(serverPath+uploadPath).isDirectory()){
            new File(serverPath+uploadPath).mkdirs();
        }
        if (!new File(serverPath+tempPath).isDirectory()){
            new File(serverPath+tempPath).mkdirs();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(5*1024); //最大缓存
        factory.setRepository(new File(serverPath+tempPath));//临时文件目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(sizeMax*1024*1024);//文件最大上限

        String filePath = null;
        try{
            List<FileItem> items = upload.parseRequest(req);
            for (int i = 0;i<items.size();i++){
                FileItem item = items.get(i);
                if(!item.isFormField()){
                    String filename = item.getName().toLowerCase();
                    String fieldName = item.getFieldName();
                    if (filename.endsWith(fileType[0])||filename.endsWith(fileType[1])||filename.endsWith(fileType[2])){
                        filePath = serverPath+uploadPath+filename;

                        File file = new File(filePath); //filePath不是filename;参数必须是(路径,文件名)
                        item.write(file);

                        writeToTrainer(fieldName,filename);
                    }else{
                        req.setAttribute("errorMsg","请确认图片类型为jpg,png,jpeg");
                        req.getRequestDispatcher("/WEB-INF/view/backstage/uploaderror.jsp").forward(req,resp);
                    }
                }else{
                    String trainerText = item.getString();
                    trainerText = new String(trainerText.getBytes("ISO-8859-1"),"UTF-8");

                    String fieldName = item.getFieldName();

                    writeToTrainer(fieldName,trainerText);
                }
            }

            trainerDao.addTrainer(trainerBean);
            resp.sendRedirect("listtrainer");
        }catch(Exception e){
            e.printStackTrace();
            req.setAttribute("errorMsg","请确认图片类型为jpg,png,jpeg");
            req.getRequestDispatcher("/WEB-INF/view/backstage/uploaderror.jsp").forward(req,resp);
        }
    }

    private void writeToTrainer(String fieldName, String value) {
        if(fieldName.equals("trainername")){
            trainerBean.setTrainername(value);
        }else if(fieldName.equals("trainerage")){
            int i = Integer.parseInt(value);
            trainerBean.setTrainerage(i);
        }else if(fieldName.equals("teachage")){
            int i = Integer.parseInt(value);
            trainerBean.setTeachage(i);
        }else if (fieldName.equals("trainerpicurl")){
            trainerBean.setTrainerpicurl(value);
        }else if(fieldName.equals("teachnum")){
            int i = Integer.parseInt(value);
            trainerBean.setTeachnum(i);
        }else if(fieldName.equals("teachcontent")){
            trainerBean.setTeachcontent(value);
        }else if (fieldName.equals("jxid")){
            int i = Integer.parseInt(value);
            trainerBean.setJxid(i);
        }else{
            System.out.println("错误");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/backstage/addtrainer.jsp").forward(req,resp);
    }
}











