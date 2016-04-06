<%@ page import="java.util.List" %>
<%@ page import="com.weixin.groups.Group" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/3/2
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分组列表</title>
</head>
<body>
    <a href="<c:url value="/login?logout"/>">Logout</a>
    <a href="<c:url value="/main.jsp"/>">返回首页</a>
    <center>
        <form action="addgroup" method="post">
            <table>
                <tr>
                    <td>新建分组名</td><input type="text" name="groupname"/>
                </tr>
            </table>
            <input type="submit" value="添加"/>
        </form>

        <table border="3" width="60%">
            <tr>
                <td width="20%">分组编号</td>
                <td width="30%">分组名称</td>
                <td width="20%">分组内用户数</td>
                <td width="20%">操作</td>
            </tr>
            <%
                List<Group> list = (List<Group>) request.getAttribute("list");
                for (int i =0;i<list.size();i++){
                    String groupId = Integer.toString(list.get(i).getGroupid());
            %>
            <tr>
                <td width="20%">
                    <%=groupId%>
                </td>
                <td width="30%">
                    <%=list.get(i).getGroupname()%>
                </td>
                <td width="20%">
                    <%=list.get(i).getCount()%>
                </td>
                <td width="20%">
                    <a href="<c:url value="/updategroup">
                    <c:param name="action" value="delete"/>
                    <c:param name="groupid" value="<%=groupId%>"/>
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
