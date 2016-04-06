<%@ page import="com.jx.bean.ComplainBean" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/15
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投诉列表</title>
</head>
<body>
    <a href="<c:url value="/login?logout"/>">Logout</a>
    <a href="<c:url value="/main.jsp"/>">返回首页</a>
    <center>
        <table border="0" width="60%">
            <tr>
                <td width="20%">
                    投诉标题
                </td>
                <td width="40%">
                    投诉内容
                </td>
                <td width="20%">
                    投诉时间
                </td>
                <td width="20%">
                    投诉人信息
                </td>
            </tr>
            <%
                List<ComplainBean> list = (List<ComplainBean>) request.getAttribute("list");
                for(int i = 0;i<list.size();i++){
            %>
            <tr>
                <td width="20%">
                    <%=list.get(i).getTitle()%>
                </td>
                <td width="40%">
                    <%=list.get(i).getComplainConnect()%>
                </td>
                <td width="20%">
                    <%=list.get(i).getComplaindate().toString()%>
                </td>
                <td width="20%">
                    <a href="<c:url value="/getuser">
                        <c:param name="action" value="openid"/>
                        <c:param name="openid" value="<%=list.get(i).getOpenid()%>"/>
                        </c:url>">获取用户信息</a>
                </td>
            </tr>

            <%
                }
            %>
        </table>
    </center>
</body>
</html>