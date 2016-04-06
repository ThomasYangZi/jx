package com.weixin;

/**
 * Created by Thomas on 2016/1/31.
 *
 */
public class TokenThread implements Runnable{
    public static String appid = AppIdAppSecret.appID;
    public static String appsecret = AppIdAppSecret.appsecret;
    public static AccessToken accessToken = null;

    public void run() {
        while(true){
            try{
                accessToken = WeixinUtil.getAccessToken(appid,appsecret);
                System.out.print(accessToken.getToken());
                if(null!=accessToken){
                    Thread.sleep((accessToken.getExpiresIn()-200)*1000);
                }else{
                    Thread.sleep(60*1000);
                }
            }catch (InterruptedException e){
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
