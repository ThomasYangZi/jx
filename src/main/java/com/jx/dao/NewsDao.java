package com.jx.dao;

import com.jx.bean.NewBean;
import com.jx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/10.
 *
 */
public class NewsDao {
    DBUtil dbutil = new DBUtil();

    public void addNews(NewBean news){
            PreparedStatement sta = null;
            String sql = "insert into news(jxid,jxname,jxpeoplenum,jxplace,smallpicurl,firstpicurl,secondpicurl,thirdpicurl,fourthpicurl,aptitude,idea,details,money) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection conn = dbutil.getConnection();
            try{
                sta=conn.prepareStatement(sql);
                sta.setInt(1,news.getIdjx());
                sta.setString(2,news.getJxname());
                sta.setInt(3,news.getJxpeoplenum());
                sta.setString(4,news.getJxplace());
                sta.setString(5,news.getSmallpicurl());
                sta.setString(6,news.getFirstpicurl());
                sta.setString(7,news.getSecondpicurl());
                sta.setString(8,news.getThirdpicurl());
                sta.setString(9,news.getFourthpicurl());
                sta.setString(10,news.getAptitude());
                sta.setString(11,news.getIdea());
                sta.setString(12,news.getDetails());
                sta.setInt(13,news.getMoney());
                sta.executeUpdate();		//执行更新
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                dbutil.closeStatement(sta);
                dbutil.closeConnection(conn);
            }
    }

    public void deleteNews(int jxid){
        PreparedStatement sta = null;
        String sql = "delete from news where id=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareCall(sql);
            sta.setInt(1,jxid);
            sta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public void updateNews(NewBean news, int jxid){
        PreparedStatement sta =null;
        String sql = "update news set smallpicurl=?,firstpicurl=?,secondpicurl=?,thirdpicurl=?,fourthpicurl=?,aptitude=?,idea=?,details=?,money=? where jxid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareCall(sql);
            sta.setString(1,news.getSmallpicurl());
            sta.setString(2,news.getFirstpicurl());
            sta.setString(3,news.getSecondpicurl());
            sta.setString(4,news.getThirdpicurl());
            sta.setString(5,news.getFourthpicurl());
            sta.setString(6,news.getAptitude());
            sta.setString(7,news.getIdea());
            sta.setString(8,news.getDetails());
            sta.setInt(9,news.getMoney());
            sta.setInt(10,jxid);

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public NewBean getById(int jxid){
        NewBean news = new NewBean();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from news where jxid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,jxid);
            res = sta.executeQuery();
            res.next();
            news.setJxname(res.getString("jxname"));
            news.setJxplace(res.getString("jxplace"));
            news.setJxpeoplenum(res.getInt("jxpeoplenum"));
            news.setSmallpicurl(res.getString("smallpicurl"));
            news.setFirstpicurl(res.getString("firstpicurl"));
            news.setSecondpicurl(res.getString("secondpicurl"));
            news.setThirdpicurl(res.getString("thirdpicurl"));
            news.setFourthpicurl(res.getString("fourthpicurl"));
            news.setAptitude(res.getString("aptitude"));
            news.setIdea(res.getString("idea"));
            news.setDetails(res.getString("details"));
            news.setMoney(res.getInt("money"));
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return news;
    }

    public int getJxid(String jxname){
        int jxid = 1;
        ResultSet res = null;
        PreparedStatement sta =null;
        String sql = "select jxid from news where jxname=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,jxname);
            res = sta.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return jxid;
    }
    public String getJxName(int jxid){
        String jxname = "你还没有报名";
        ResultSet res = null;
        PreparedStatement sta =null;
        String sql = "select jxname from news where jxid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,jxid);
            res = sta.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return jxname;
    }

    public List<NewBean> listNews(){
        List<NewBean> list = new ArrayList<NewBean>();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from news";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            res = sta.executeQuery();

            while(res.next()){
                NewBean news = new NewBean();
                news.setIdjx(res.getInt("jxid"));
                news.setJxname(res.getString("jxname"));
                news.setJxpeoplenum(res.getInt("jxpeoplenum"));
                news.setSmallpicurl(res.getString("smallpicurl"));
                news.setAptitude(res.getString("aptitude"));
                news.setJxplace(res.getString("jxplace"));
                news.setMoney(res.getInt("money"));
                list.add(news);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return list;
    }

}

