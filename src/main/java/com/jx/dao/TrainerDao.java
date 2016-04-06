package com.jx.dao;

import com.jx.bean.TrainerBean;
import com.jx.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2016/2/15.
 *
 */
public class TrainerDao  {
    DBUtil dbutil = new DBUtil();

    public void addTrainer(TrainerBean trainer){
        PreparedStatement sta = null;
        String sql = "insert into trainer(trainername,trainerage,teachage,trainerpicurl,teachnum,teachcontent,jxid) values(?,?,?,?,?,?,?)";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setString(1,trainer.getTrainername());
            sta.setInt(2,trainer.getTrainerage());
            sta.setInt(3,trainer.getTeachage());
            sta.setString(4,trainer.getTrainerpicurl());
            sta.setInt(5,trainer.getTeachnum());
            sta.setString(6,trainer.getTeachcontent());
            sta.setInt(7,trainer.getJxid());
            sta.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public void deleteTrainer(int trainerId){
        PreparedStatement sta = null;
        String sql = "delete from trainer where id=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,trainerId);
            sta.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            dbutil.closeStatement(sta);
            dbutil.closeConnection(conn);
        }
    }

    public List<TrainerBean> listTrainerByJxid(int jxid){
        List<TrainerBean> list = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from trainer where jxid=?";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            sta.setInt(1,jxid);
            res = sta.executeQuery();

            while (res.next()){
                TrainerBean trainerBean = new TrainerBean();
                trainerBean.setTrainerId(res.getInt("trainerId"));
                trainerBean.setTrainername(res.getString("trainername"));
                trainerBean.setTrainerage(res.getInt("trainerage"));
                trainerBean.setTeachage(res.getInt("teachage"));
                trainerBean.setTrainerpicurl(res.getString("trainerpicurl"));
                trainerBean.setTeachnum(res.getInt("teachnum"));
                trainerBean.setTeachcontent(res.getString("teachcontent"));
                list.add(trainerBean);
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

    public List<TrainerBean> listTrainer(){
        List<TrainerBean> list = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement sta = null;
        String sql = "select * from trainer";
        Connection conn = dbutil.getConnection();
        try{
            sta = conn.prepareStatement(sql);
            res = sta.executeQuery();

            while (res.next()){
                TrainerBean trainerBean = new TrainerBean();
                trainerBean.setTrainerId(res.getInt("trainerId"));
                trainerBean.setTrainername(res.getString("trainername"));
                trainerBean.setTrainerage(res.getInt("trainerage"));
                trainerBean.setTeachage(res.getInt("teachage"));
                trainerBean.setTrainerpicurl(res.getString("trainerpicurl"));
                trainerBean.setTeachnum(res.getInt("teachnum"));
                trainerBean.setTeachcontent(res.getString("teachcontent"));
                trainerBean.setJxid(res.getInt("jxid"));
                list.add(trainerBean);
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
