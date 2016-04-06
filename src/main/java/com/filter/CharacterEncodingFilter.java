package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Thomas on 2016/2/9.
 * 编码过滤器
 */
public class CharacterEncodingFilter implements Filter{
    protected FilterConfig filterConfig;
    protected String encodingName;
    protected boolean enable;

    public CharacterEncodingFilter() {
        this.encodingName = "UTF-8";
        this.enable = false;
    }


    public void destroy() {
        // Auto-generated method stub

    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // Auto-generated method stub
        if(this.enable){
            request.setCharacterEncoding(this.encodingName);
        }
        chain.doFilter(request, response);
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        //  Auto-generated method stub
        this.filterConfig = filterConfig;
        loadConfigParams();
    }


    private void loadConfigParams() {
        // Auto-generated method stub
        this.encodingName = this.filterConfig.getInitParameter("encoding");

        String strIgnoreFlag = this.filterConfig.getInitParameter("enable");
        if(strIgnoreFlag.equalsIgnoreCase("true")){
            this.enable = true;
        }else{
            this.enable = false;
        }
    }
}
