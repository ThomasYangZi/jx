package com.jx.bean;

import java.util.Date;

/**
 * Created by Thomas on 2016/2/10.
 *
 */
public class UserBean {
    private int iduser;
    private String openid;
    private String username;    //姓名
    private String userphone;   //电话
    private int jxid;           //报名的驾校
    private int usernum;        //签到课时
    private Date userdate;      //报名日期

    public int getIduser() {
        return iduser;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public int getJxid() {
        return jxid;
    }

    public void setJxid(int jxid) {
        this.jxid = jxid;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public Date getUserdate() {
        return userdate;
    }

    public void setUserdate(Date userdate) {
        this.userdate= userdate;
    }
}
