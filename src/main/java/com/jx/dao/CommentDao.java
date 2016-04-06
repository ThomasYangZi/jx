package com.jx.dao;

import com.jx.bean.CommentBean;
import com.jx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/11.
 *
 */
public class CommentDao {
    DBUtil dbutil = new DBUtil();

    public void addComment(CommentBean comment){
        PreparedStatement sta = null;
        String sql = "insert into comment(connects,username,jxid) values(?,?,?) ";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,comment.getConnects());
            sta.setString(2,comment.getUsername());
            sta.setInt(3,comment.getJxid());
            sta.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public List getCommentByjxid(int jxid){
        List list = new ArrayList();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from comment where jxid=? order by createtime desc";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,jxid);
            res = sta.executeQuery();

            while(res.next()){
                CommentBean comment = new CommentBean();
                comment.setId(res.getInt("id"));
                comment.setConnects(res.getString("connects"));
                comment.setUsername(res.getString("username"));
                comment.setCreatetime(res.getDate("createtime"));
                list.add(comment);
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
