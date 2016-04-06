<%@ page import="com.jx.bean.NewBean" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/13
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>驾校列表</title>
</head>
<body>
<a href="<c:url value="/login?logout"/>">Logout</a>
<a href="<c:url value="/main.jsp"/>">返回首页</a>
    <center>
        <a href ="<c:url value="/addnews"/>">添加驾校</a>
        <table border="0" width="60%">
            <tr>
                <td width="20%">
                    驾校编号
                </td>
                <td width="40%">
                    驾校名称
                </td >
                <td width="20%">
                    驾校报名人数
                </td>
                <td width="20%">
                    操作
                </td>
            </tr>
            <%
                List<NewBean> list = (List<NewBean>) request.getAttribute("list");
                for (int i = 0; i < list.size(); i++) {
                    String idString = Integer.toString(list.get(i).getIdjx());
            %>
            <tr>

                <td width="20%">
                    <%=idString%>
                </td>
                <td width="40%">
                    <%=list.get(i).getJxname()%>
                </td>
                <td width="20%">
                   <%=list.get(i).getJxpeoplenum()%>
                </td>
                <td width="20%">
                    <a href="<c:url value="/updatenews">
                    <c:param name="action" value="update"/>
                    <c:param name="jxid" value="<%=idString%>"/>
                    </c:url> ">更新</a>
                </td>
                <td>
                    <a href="<c:url value="/updatenews">
                    <c:param name="action" value="delete"/>
                    <c:param name="jxid" value="<%=idString%>"/>
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
