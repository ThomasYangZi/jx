package com.jx.bean;

import java.util.Date;

/**
 * Created by Thomas on 2016/2/10.
 *
 */
public class CommentBean {
    private int commentid;
    private String connects;     //评论正文
    private String username;    //用户名
    private Date createtime;    //创建时间
    private int jxid;

    public int getId() {
        return commentid;
    }

    public void setId(int commentid) {
        this.commentid = commentid;
    }

    public void setJxid(int jxid) {
        this.jxid = jxid;
    }

    public int getJxid(){
        return jxid;
    }

    public String getConnects() {
        return connects;
    }

    public void setConnects(String connects) {
        this.connects = connects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
