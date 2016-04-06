<%@ page import="java.util.List" %>
<%@ page import="com.jx.bean.TrainerBean" %>
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
    <a href ="<c:url value="/addtrainer"/>">添加教练</a>
    <table border="0" width="60%">
        <tr>
            <td width="20%">
                教练编号
            </td>
            <td width="30%">
            教练头像
            </td>
            <td width="20%">
                教练姓名
            </td >
            <td width="10%">
                服务星级
            </td>
            <td width="20%">
                操作
            </td>
        </tr>
        <%
            List<TrainerBean> list = (List<TrainerBean>) request.getAttribute("list");
            for (int i = 0; i < list.size(); i++) {
                String idString = Integer.toString(list.get(i).getTrainerId());
        %>
        <tr>

            <td width="20%">
                <%=idString%>
            </td>
            <td width="30%">
            <img src="/resource/image/jxtrainer/<%=list.get(i).getTrainerpicurl()%>" width="50" height="50" alt="年龄:<%=list.get(i).getTrainerage()%>"/>
            </td>
            <td width="20%">
                <%=list.get(i).getTrainername()%>
            </td>
            <td width="10%">
                <%=list.get(i).getTeachnum()%>
            </td>
            <td width="20%">
                <a href="<c:url value="/updatetrainer">
                    <c:param name="action" value="update"/>
                    <c:param name="trainerid" value="<%=idString%>"/>
                    </c:url> ">更新</a>
            </td>
            <td>
                <a href="<c:url value="/updatetrainer">
                    <c:param name="action" value="delete"/>
                    <c:param name="trainerid" value="<%=idString%>"/>
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

