package com.jx.dao;

import com.jx.bean.ComplainBean;
import com.jx.util.DBUtil;
import com.jx.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/11.
 *
 */
public class ComplainDao {
    DBUtil dbutil = new DBUtil();
    DateUtil dateUtil = new DateUtil();
    Date date = dateUtil.gaindate();

    public void addComplain(ComplainBean complain){
        PreparedStatement sta = null;
        String sql = "insert into complain(title,complainconnect,openid,complaindate) values(?,?,?,?) ";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,complain.getTitle());
            sta.setString(2,complain.getComplainConnect());
            sta.setString(3,complain.getOpenid());
            sta.setDate(4,date);
            sta.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public List showComplain(){
        List list = new ArrayList();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from complain";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            res = sta.executeQuery();

            while(res.next()){
                ComplainBean complain = new ComplainBean();
                complain.setComplainid(res.getInt("complainid"));
                complain.setTitle(res.getString("title"));
                complain.setComplainConnect(res.getString("complainconnect"));
                complain.setComplaindate(res.getDate("complaindate"));
                complain.setOpenid(res.getString("openid"));
                list.add(complain);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
