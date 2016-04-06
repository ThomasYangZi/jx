package com.jx.dao;

import com.jx.bean.UserBean;
import com.jx.util.DBUtil;
import com.jx.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/11.
 *
 */
public class UserDao {
    DBUtil dbutil = new DBUtil();
    DateUtil dateUtil = new DateUtil();
    Date date = dateUtil.gaindate();

    public void addUser(UserBean user){
        PreparedStatement sta = null;
        String sql = "insert into user(openid,username,userphone,jxid,userdate) values(?,?,?,?,?)";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,user.getOpenid());
            sta.setString(2,user.getUsername());
            sta.setString(3,user.getUserphone());
            sta.setInt(4,user.getJxid());
            sta.setDate(5,date);
            sta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }


    public void deleteUser(String openid){
        PreparedStatement sta = null;
        String sql = "delete from user where openid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareCall(sql);
            sta.setString(1,openid);
            sta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public UserBean getByopenid(String openid){
        UserBean user = new UserBean();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from user where openid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,openid);
            res = sta.executeQuery();
            res.next();
            user.setOpenid(res.getString("openid"));
            user.setUsername(res.getString("username"));
            user.setUserphone(res.getString("userphone"));
            user.setJxid(res.getInt("jxid"));
            user.setUsernum(res.getInt("usernum"));
            user.setUserdate(res.getDate("userdate"));
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return user;
    }

    public UserBean getByUserphone(String userphone){
        UserBean user = new UserBean();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from user where userphone=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,userphone);
            res = sta.executeQuery();
            res.next();
            user.setOpenid(res.getString("openid"));
            user.setUsername(res.getString("username"));
            user.setUserphone(res.getString("userphone"));
            user.setJxid(res.getInt("jxid"));
            user.setUsernum(res.getInt("usernum"));
            user.setUserdate(res.getDate("userdate"));
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return user;
    }


    public List<UserBean> listUser(int usernum,int jxid){
        List<UserBean> users = new ArrayList<UserBean>();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from user where usernum=? and jxid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,usernum);
            sta.setInt(2,jxid);
            res = sta.executeQuery();

            while(res.next()){
                UserBean user = new UserBean();
                user.setUsername(res.getString("username"));
                user.setUserphone(res.getString("userphone"));
                user.setUserdate(res.getDate("userdate"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return users;
    }

    public List<UserBean> getAllUser(){
        List<UserBean> users = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from user";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            res = sta.executeQuery();
            while(res.next()){
                UserBean user = new UserBean();
                user.setUsername((res.getString("username")));
                user.setUserphone((res.getString("userphone")));
                user.setUserdate(res.getDate("userdate"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeResultSet(res);
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
        return users;
    }

}
