package com.jx.action.news;

import com.jx.bean.NewBean;
import com.jx.dao.NewsDao;
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
 * Created by Thomas on 2016/2/13.
 *
 */
@WebServlet(
        name="UpdateNews",
        urlPatterns = "/updatenews"
)
public class UpdateNews extends HttpServlet {
    NewsDao newsDao = new NewsDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String jxid = req.getParameter("jxid");
        if(action.equals("update")){
            this.updateNews(req, resp, jxid);
        }else if(action.equals("delete")){
            this.deleteNews(req, resp, jxid);
        }
    }

    private void deleteNews(HttpServletRequest req, HttpServletResponse resp, String jxid) throws ServletException, IOException{
        int intjxid = Integer.parseInt(jxid);
        newsDao.deleteNews(intjxid);
        resp.sendRedirect("/listnews");
    }

    public void updateNews(HttpServletRequest req,HttpServletResponse resp, String jxid) throws ServletException, IOException{
        req.setAttribute("jxid",jxid);
        req.getRequestDispatcher("/WEB-INF/view/backstage/updatejx.jsp").forward(req,resp);
    }


    NewBean news = new NewBean();

    private String uploadPath = "resources/image/jxnews/";   //长传文件目录
    private String tempPath = "resources/image/jxnewstmp/";  //临时文件目录
    private String serverPath = null;
    private String[] fileType = new String[]{".jpg",".png",".jpeg"};
    private int sizeMax = 5;    //图片最大上限


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 服务器端根目录
        String serverPath = getServletContext().getRealPath("/").replace("\\", "/");

        //Servlet初始化时执行,如果上传文件目录不存在则自动创建
        if(!new File(serverPath+uploadPath).isDirectory()){
            new File(serverPath+uploadPath).mkdirs();
        }
        if(!new File(serverPath+tempPath).isDirectory()){
            new File(serverPath+tempPath).mkdirs();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(5*1024); //最大缓存
        factory.setRepository(new File(serverPath+tempPath));//临时文件目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(sizeMax*1024*1024);//文件最大上限

        String filePath = null;
        try{
            List<FileItem> items =upload.parseRequest(req);
            for(int i=0;i<items.size();i++){
                FileItem item = items.get(i);
                if(!item.isFormField()){
                    String filename = item.getName().toLowerCase(); //获取文件名
                    String fieldName= item.getFieldName();          //获取表单字段名
                    if(filename.endsWith(fileType[0])||filename.endsWith(fileType[1])||filename.endsWith(fileType[2])){
                        //检查文件后缀
                        filePath = serverPath+uploadPath+filename;

                        File file = new File(filePath);
                        item.write(file);
                        //获取实体
                        writeToNews(fieldName, filename);
                    }else{
                        req.setAttribute("errorMsg","请确认图片类型为jpg,png,jpeg");
                        req.getRequestDispatcher("/WEB-INF/view/backstage/uploaderror.jsp").forward(req,resp);
                    }
                }else{
                    String newsText = item.getString();     //非文件流
                    newsText = new String(newsText.getBytes("ISO-8859-1"),"UTF-8");

                    String fieldName = item.getFieldName();
                    //获取实体
                    writeToNews(fieldName, newsText);
                }
            }

            int jxid = Integer.parseInt(req.getParameter("jxid"));
            newsDao.updateNews(news,jxid);

        }catch(Exception e){
            e.printStackTrace();
            req.setAttribute("errorMsg","请确认图片类型为jpg,png,jpeg");
            req.getRequestDispatcher("/WEB-INF/view/backstage/uploaderror.jsp").forward(req,resp);
        }

    }


    private void writeToNews(String fieldName, String value) {

       if(fieldName.equals("jxplace")&&value!=null){
            news.setJxplace(value);
        }else if(fieldName.equals("smallpic")){
            news.setSmallpicurl(value);
        }else if(fieldName.equals("firstpic")){
            news.setFirstpicurl(value);
        }else if(fieldName.equals("secondpic")){
            news.setSecondpicurl(value);
        }else if(fieldName.equals("thirdpic")){
            news.setThirdpicurl(value);
        }else if(fieldName.equals("fourthpic")){
            news.setFourthpicurl(value);
        }else if(fieldName.equals("aptitude")){
            news.setAptitude(value);
        }else if(fieldName.equals("idea")){
            news.setIdea(value);
        }else if(fieldName.equals("details")){
            news.setDetails(value);
        }else if(fieldName.equals("money")){
            int i = Integer.parseInt(value);
            news.setMoney(i);
        }else{
            System.out.print("我日，这一坨有一个参数错误");
        }

    }



}
