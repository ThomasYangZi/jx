package com.jx.bean;

import java.util.Date;

/**
 * Created by Thomas on 2016/2/10.
 *
 */
public class ComplainBean {
    private int complainid;
    private String title;
    private String complainConnect;
    private String openid;
    private Date complaindate;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setComplainid(int complainid) {
        this.complainid = complainid;
    }

    public int getComplainid() {
        return complainid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComplainConnect() {
        return complainConnect;
    }

    public void setComplainConnect(String complainConnect) {
        this.complainConnect = complainConnect;
    }

    public Date getComplaindate() {
        return complaindate;
    }

    public void setComplaindate(Date complaindate) {
        this.complaindate = complaindate;
    }
}
