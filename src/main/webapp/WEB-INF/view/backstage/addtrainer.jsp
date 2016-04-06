<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/12
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加教练</title>
</head>
<body>
<a href="<c:url value="/login?logout"/>">Logout</a>
<a href="<c:url value="/main.jsp"/>">返回首页</a>
<center>
    <form action="addtrainer" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <th>教练姓名</th>  <td><input type="text" name = "trainername"/></td>
            </tr>
            <tr>
                <th>教练年龄</th>  <td><input type="text" name = "trainerage"></td>
            </tr>
            <tr>
                <th>教学年龄</th>  <td><input type="text" name = "teachage"/></td>
            </tr>
            <tr>
                <th>教练头像</th>  <td><input type="file" name="trainerpicurl"></td>
            </tr>
            <tr>
                <th>教练星级</th>  <td><input type="text" name = "teachnum"/></td>
            </tr>
            <tr>
                <th>教学内容</th>  <td><input type="text" name = "teachcontent"/></td>
            </tr>
            <tr>
                <th>驾校编号</th>  <td><input type="text" name = "jxid"/></td>
            </tr>
        </table>
        <input type="submit" value="添加"/>
    </form>
</center>
</body>
</html>
