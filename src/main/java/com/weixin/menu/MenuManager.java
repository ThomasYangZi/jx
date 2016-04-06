package com.weixin.menu;

import com.weixin.AccessToken;
import com.weixin.AppIdAppSecret;
import com.weixin.WeixinUtil;


/**
 * Created by Thomas on 2016/2/1.
 * 菜单管理类
 */
public class MenuManager {


    public static void main(String[] args) {
        String appId = AppIdAppSecret.appID;
        String appSecret = AppIdAppSecret.appsecret;
        //有没有必要
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);


        //AccessToken at = TokenThread.accessToken;
        // 调用接口创建菜单
        if (null != at) {
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());
            if (0 == result) {
                System.out.println("菜单创建成功");
            } else {
                System.out.println("失败"+result);
            }
        }
    }
    private static Menu getMenu() {
        /*
        CommonButton btn11 = new CommonButton();
        btn11.setName("考试安排");
        btn11.setType("click");
        btn11.setKey("11");
        ViewButton btn12 = new ViewButton();
        btn12.setName("我要报名");
        btn12.setType("view");
        btn12.setUrl("http://101.200.159.23/index.jsp");
        CommonButton btn13 = new CommonButton();
        btn13.setName("驾校推荐");
        btn13.setType("click");
        btn13.setKey("13");
        */
        ViewButton btn11 = new ViewButton();
        btn11.setName("进入首页");
        btn11.setType("view");
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx776c1daa6ebaa4da&redirect_uri=http://www.genwoxueche.com/oauth&response_type=code&scope=snsapi_userinfo&state=index#wechat_redirect");

        ViewButton btn21 = new ViewButton();
        btn21.setName("我要报名");
        btn21.setType("view");
        btn21.setUrl("http://www.genwoxueche.com/yuyuebiaodan.html");
        /*
        ViewButton btn22 = new ViewButton();
        btn22.setName("考试题库");
        btn22.setType("view");
        btn22.setUrl("http://101.200.159.23/index.jsp");
        ViewButton btn23 = new ViewButton();
        btn23.setName("重要通知");
        btn23.setType("view");
        btn23.setUrl("http://101.200.159.23/index.jsp");
        */

        CommonButton btn31 = new CommonButton();
        btn31.setName("练车签到");
        btn31.setType("click");
        btn31.setKey("31");
        ViewButton btn32 = new ViewButton();
        btn32.setName("自主约考");
        btn32.setType("view");
        btn32.setUrl("http://wcg.whjg.gov.cn/preasign/wx/preasign/form");
        CommonButton btn33 = new CommonButton();
        btn33.setName("考试安排");
        btn33.setType("click");
        btn33.setKey("33");
        ViewButton btn34 = new ViewButton();
        btn34.setName("学车进度");
        btn34.setType("view");
        btn34.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx776c1daa6ebaa4da&redirect_uri=http://www.genwoxueche.com/oauth&response_type=code&scope=snsapi_userinfo&state=personalproceeding#wechat_redirect");
        ViewButton btn35 = new ViewButton();
        btn35.setName("个人中心");
        btn35.setType("view");
        btn35.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx776c1daa6ebaa4da&redirect_uri=http://www.genwoxueche.com/oauth&response_type=code&scope=snsapi_userinfo&state=personalcenter#wechat_redirect");

        /*
        ComplexButton btn1 = new ComplexButton();
        btn1.setName("我要学车");
        btn1.setSub_button(new Button[]{btn11,btn12,btn13,btn14});

        ComplexButton btn2 = new ComplexButton();
        btn2.setName("跟我学车");
        btn2.setSub_button(new Button[]{btn21,btn22,btn23});
        */
        ComplexButton btn3 = new ComplexButton();
        btn3.setName("功能列表");
        btn3.setSub_button(new Button[]{btn31,btn32,btn33,btn34,btn35});

        Menu menu = new Menu();
        menu.setButton(new Button[]{btn11,btn21,btn3});
        return menu;
    }

}