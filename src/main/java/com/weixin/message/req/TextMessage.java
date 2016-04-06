package com.weixin.message.req;

/**
 * Created by Thomas on 2016/2/3.
 * 文本消息
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
