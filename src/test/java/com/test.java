package com;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Thomas on 2016/2/24.
 *
 */
public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://101.200.159.23:3306/jx";
        String username = "yzf";
        String password = "KOYb4G5Q";

        Class.forName(driverName);
        Connection conn = null;
        conn = DriverManager.getConnection(url, username, password);
        String sql = "select * from comment";
        ResultSet res = null;
        PreparedStatement sta = conn.prepareStatement(sql);
        res = sta.executeQuery();
        java.sql.Timestamp createtime = null;
        while (res.next()) {        //?
            createtime = res.getTimestamp("createtime");
        }
        System.out.println(createtime);
        java.util.Date date = new Date(createtime.getTime());
        SimpleDateFormat tempDate = new SimpleDateFormat("MM-dd HH:mm");
        String d = tempDate.format(date);
        System.out.println(d);
        res.close();
        sta.close();
        conn.close();
    }

}
