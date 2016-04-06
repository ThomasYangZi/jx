<%@ page import="com.jx.bean.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/23
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息页</title>
</head>
<body>
<a href="<c:url value="/login?logout"/>">Logout</a>
<a href="<c:url value="/main.jsp"/>">返回首页</a>
<center>
    <form action="/getuser" method="post">
        <table border="0" width="60%">
            <tr>
                <th>电话号码</th> <td><input type="text" name="userphone"/></td>
            </tr>
        </table>
        <input type="submit" value="查询"/>
    </form>

    <table border="0" width="60%">
        <tr>
            <td width="20%">
                用户姓名
            </td>
            <td width="20%">
                电话号码
            </td >
            <td width="20%">
                报名日期
            </td>
            <td width="20%">
                报名驾校
            </td>
            <td width="20%">
                操作
            </td>
        </tr>
        <%
            UserBean userBean = new UserBean();
            userBean = (UserBean) request.getAttribute("userBean");
        %>
        <tr>
            <td width="20%">
                <%=userBean.getUsername()%>
            </td>
            <td width="20%">
                <%=userBean.getUserphone()%>
            </td>
            <td width="20%">
                <%=userBean.getUserdate().toString()%>
            </td>
            <td width="20%">
                <%=userBean.getJxid()%>
            </td>
            <td width="20%">
                <a href="<c:url value="/updatenews">
                    <c:param name="action" value="update"/>
                    <c:param name="jxid" value="<%=userBean.getOpenid()%>"/>
                    </c:url> ">更新</a>
            </td>
            <td>
                <a href="<c:url value="/updatenews">
                    <c:param name="action" value="delete"/>
                    <c:param name="jxid" value="<%=userBean.getOpenid()%>"/>
                    </c:url> ">删除</a>
            </td>
        </tr>
    </table>
</center>

</body>
</html>
