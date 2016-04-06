package com.weixin;

/**
 * Created by Thomas on 2016/1/31.
 * 接口调用凭证
 */
public class AccessToken  {
    //获得的凭证access_token
    private String token;
    //凭证有效时间expires_in,单位秒
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
