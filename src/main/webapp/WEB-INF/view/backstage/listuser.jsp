<%@ page import="com.jx.bean.UserBean" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/23
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<a href="<c:url value="/login?logout"/>">Logout</a>
<a href="<c:url value="/main.jsp"/>">返回首页</a>
<center>
    <form action="/listuser" method="post">
        <table border="0" width="60%">
            <tr>
                <th>签到天数</th> <td><input type="text" name="usernum"/></td>
            </tr>
            <tr>
                <th>驾校编号</th> <td><input type="text" name="jxid"></td>
            </tr>
        </table>
        <input type="submit" value="查询"/>
    </form>
    <table border="0" width="60%">
        <tr>
            <td width="30%">
                用户姓名
            </td>
            <td width="30%">
                电话号码
            </td >
            <td width="20%">
                报名日期
            </td>
            <td width="20%">
                操作
            </td>
        </tr>
        <%
            List<UserBean> list = (List<UserBean>) request.getAttribute("list");
            for (int i = 0; i < list.size(); i++) {
        %>
        <tr>

            <td width="20%">
                <%=list.get(i).getUsername()%>
            </td>
            <td width="30%">
                <%=list.get(i).getUserphone()%>
            </td>
            <td width="20%">
                <%=list.get(i).getUserdate().toString()%>
            </td>
            <td width="20%">
                <a href="<c:url value="/updateusergroup">
                    <c:param name="openId" value="<%=list.get(i).getOpenid()%>"/>
                    </c:url> ">更新用户分组</a>
            </td>
            <td>
                <a href="<c:url value="/updateuser">
                    <c:param name="action" value="delete"/>
                    <c:param name="openId" value="<%=list.get(i).getOpenid()%>"/>
                    </c:url> ">删除</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</center>
</body>
</html>
