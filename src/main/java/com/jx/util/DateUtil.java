package com.jx.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Thomas on 2016/2/23.
 * 字符串转Date工具
 * Date转字符串用java.sql.Date.toString()
 */
public class DateUtil  {
    public Date gaindate(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = tempDate.format(new java.util.Date());
        Date date = null;
        try{
            date = Date.valueOf(datetime);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return date;
    }
}
