package com.jx.util;

import java.sql.*;

/**
 * Created by Thomas on 2016/2/10.
 * 数据库连接工具
 */
public class DBUtil {


    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://101.200.159.23:3306/jx";
    private String username = "yzf";
    private String password = "KOYb4G5Q";

    public Connection getConnection(){
        try{
            Class.forName(this.driverName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(this.url,this.username,this.password);
            if(!conn.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public void closeConnection(Connection conn){
        if(null!=conn){
            try{
                if(!conn.isClosed()){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void closeStatement(Statement sta){
        if(null!=sta){
            try{
                if(!sta.isClosed()){
                    sta.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void closeResultSet(ResultSet res){
        if(null != res){
            try{
                if(!res.isClosed()){
                    res.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
