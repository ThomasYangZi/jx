package com.weixin.groups;

/**
 * Created by Thomas on 2016/2/24.
 *
 */
public class Group {
    private int groupid;        //分组id
    private String groupname;   //分组名称
    private int count;          //分组内用户数量

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
