package com.weixin;

import com.weixin.message.MessageUtil;
import com.weixin.message.resp.TextMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by Thomas on 2016/1/30.
 * 微信收发器
 */
@WebServlet(
        name = "ConnectServlet",
        urlPatterns = "/greeting",
        loadOnStartup = 1
)
public class ConnectServlet extends HttpServlet {

    public ConnectServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //微信加密签名
        String signature = req.getParameter("signature");
        //时间戳
        String timestamp = req.getParameter("timestamp");
        //随机数
        String nonce = req.getParameter("nonce");
        //随机字符串
        String echostr = req.getParameter("echostr");

        String token = "yyy";
        String[] strings = {token, timestamp, nonce};
        Arrays.sort(strings);//字典排序
        String str = strings[0] + strings[1] + strings[2];
        //sha1加密
        String digest = new SHA1().getDigestOfString(str.getBytes()).toLowerCase();

        //确认请求来至微信
        if(digest.equals(signature)){
            resp.getWriter().print(echostr);
        }
    }



    /**
     * 处理微信服务器发来的消息
    */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 消息的接收、处理、响应
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = ConnectServlet.processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    private static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求无效！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            textMessage.setContent("无效请求，请访问<a href=\"http://www.genwoxueche.com/index.jsp\">主页</a>");
            // 将文本消息对象转换成xml字符串
            respMessage = MessageUtil.textMessageToXml(textMessage);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //接收用户发送的文本消息
                String content = requestMap.get("Content");
                int contentInt = Integer.parseInt(content);

                //获取上次请求点击菜单时的时间
                long clickTime = ClickTimeMap.getClickTime(fromUserName);
                if (clickTime!=0&&(System.currentTimeMillis()-clickTime<60*1000)){
                    if (contentInt>=1&&contentInt<=12){
                        //DO
                        //移动分组
                        respContent = "你已经签到成功";
                        //usernum更新

                        //发送模板消息
                    }else {
                        respContent = "你输入课时数不合法（不在1-12之间）";
                    }
                }else{
                    //发送文字提醒
                    respContent = "您输入的内容无效，或练车签到超时（点击练车签到后请在1分钟内输入今日练车课时数）";
                }

                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
              /*
                // 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setFuncFlag(0);

                List<Article> articleList = new ArrayList<Article>();

                // 单图文消息
                if ("1".equals(content)) {
                    Article article = new Article();
                    article.setTitle("驾校");
                    article.setDescription("驾校");
                    article.setPicUrl("http://101.200.159.23/resource/image/message/1.png");
                    article.setUrl("http://101.200.159.23/view/jsp/index.jsp");
                    articleList.add(article);
                    // 设置图文消息个数
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);

                }
                // 单图文消息---不含图片
                else if ("2".equals(content)) {
                    Article article = new Article();
                    article.setTitle("驾校");
                    // 图文消息中可以使用QQ表情、符号表情
                    article.setDescription("啦啦啦啦啦啊啦"
                            + "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n\n啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n\n啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
                    // 将图片置为空
                    article.setPicUrl("");
                    article.setUrl("http://101.200.159.23/view/jsp/index.jsp");
                    articleList.add(article);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }
                // 多图文消息
                else if ("3".equals(content)) {
                    Article article1 = new Article();
                    article1.setTitle("啦啦啦啦啦啦");
                    article1.setDescription("");
                    article1.setPicUrl("http://101.200.159.23/resource/image/message/1.png");
                    article1.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article2 = new Article();
                    article2.setTitle("拉拉拉拉啊啦");
                    article2.setDescription("");
                    article2.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article2.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article3 = new Article();
                    article3.setTitle("啦啦啦啦啊啦");
                    article3.setDescription("");
                    article3.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article3.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }
                // 多图文消息---首条消息不含图片
                else if ("4".equals(content)) {
                    Article article1 = new Article();
                    article1.setTitle("驾校");
                    article1.setDescription("");
                    // 将图片置为空
                    article1.setPicUrl("");
                    article1.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article2 = new Article();
                    article2.setTitle("拉拉拉拉阿拉拉拉啊拉");
                    article2.setDescription("");
                    article2.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article2.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article3 = new Article();
                    article3.setTitle("啦啦啦啦啦啦啦啦啦");
                    article3.setDescription("");
                    article3.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article3.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article4 = new Article();
                    article4.setTitle("啦啦啦啦啦啦啦啦");
                    article4.setDescription("");
                    article4.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article4.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    articleList.add(article4);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }
                // 多图文消息---最后一条消息不含图片
                    else if ("5".equals(content)) {

                    Article article5 = new Article();
                    article5.setTitle("啦");
                    article5.setDescription("");
                    article5.setPicUrl("http://101.200.159.23/resource/image/message/1.png");
                    article5.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article6 = new Article();
                    article6.setTitle("啦");
                    article6.setDescription("");
                    article6.setPicUrl("http://101.200.159.23/resource/image/message/2.png");
                    article6.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    Article article7 = new Article();
                    article7.setTitle("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
                    article7.setDescription("");
                    // 将图片置为空
                    article7.setPicUrl("");
                    article7.setUrl("http://101.200.159.23/view/jsp/index.jsp");

                    articleList.add(article5);
                    articleList.add(article6);
                    articleList.add(article7);
                    newsMessage.setArticleCount(articleList.size());
                    newsMessage.setArticles(articleList);
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
                }
                */

            }

            else {
                // 图片消息
                if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                    respContent = "您发送的是图片消息！";
                }
                // 地理位置消息
                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                    respContent = "您发送的是地理位置消息！";
                }
                // 链接消息
                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                    respContent = "您发送的是链接消息！";
                }
                // 音频消息
                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                    respContent = "您发送的是音频消息！";
                }
                // 事件推送
                else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                    // 事件类型
                    String eventType = requestMap.get("Event");
                    // 订阅
                    if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("跟我学车,让学车变得更简单!").append("\n\n");
                        buffer.append("即将上线,敬请期待!");
                        respContent = buffer.toString();
                    }
                    // 取消订阅
                    else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                        // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                    }
                    // 自定义菜单点击事件
                    else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                        String eventKey = requestMap.get("EventKey");

                        if (eventKey.equals("31")) {
                            respContent = "请在一分钟内输入今日练车课时数（一小时为一课时）";

                            long clickTime = System.currentTimeMillis();
                            ClickTimeMap.addClickTimeMap(fromUserName,clickTime);

                        } else if (eventKey.equals("32")) {
                            respContent = "即将上线，敬请期待";
                        } else if (eventKey.equals("33")) {

                            respContent = "即将上线";
                        } else if (eventKey.equals("21")) {
                            respContent = "报名功能马上开启";
                        }
                    }
                }

                textMessage.setContent(respContent);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}

