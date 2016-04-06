package com.jx.bean;

/**
 * Created by Thomas on 2016/2/15.
 * 教练实体
 */
public class TrainerBean {
    private int trainerId;
    private String trainername; //姓名
    private int trainerage;     //年龄
    private int teachage;       //教龄
    private String trainerpicurl;//头像
    private int teachnum;       //服务星级
    private String teachcontent;//教学内容
    private int jxid;           //驾校id

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainername() {
        return trainername;
    }

    public void setTrainername(String trainername) {
        this.trainername = trainername;
    }

    public int getTrainerage() {
        return trainerage;
    }

    public void setTrainerage(int trainerage) {
        this.trainerage = trainerage;
    }

    public int getTeachage() {
        return teachage;
    }

    public void setTeachage(int teachage) {
        this.teachage = teachage;
    }

    public String getTrainerpicurl() {
        return trainerpicurl;
    }

    public void setTrainerpicurl(String trainerpicurl) {
        this.trainerpicurl = trainerpicurl;
    }

    public int getTeachnum() {
        return teachnum;
    }

    public void setTeachnum(int teachnum) {
        this.teachnum = teachnum;
    }

    public String getTeachcontent() {
        return teachcontent;
    }

    public void setTeachcontent(String teachcontent) {
        this.teachcontent = teachcontent;
    }

    public int getJxid() {
        return jxid;
    }

    public void setJxid(int jxid) {
        this.jxid = jxid;
    }
}
