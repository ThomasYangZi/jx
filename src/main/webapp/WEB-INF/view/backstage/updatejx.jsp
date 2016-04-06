<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/2/13
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新驾校</title>
</head>
<body>
    <a href="<c:url value="/login?logout"/>">Logout</a>
    <a href="<c:url value="/main.jsp"/>">返回首页</a>
    <center>
    <form action="addnews" method="post" enctype="multipart/form-data">
        <table>
            <%String idString = request.getAttribute("jxid").toString();%>
            <tr>
                <th>驾校编号</th>  <td><%=idString%></td>
            </tr>
            <tr>
                <th>驾校地址</th>  <td><input type="text" name = "jxplace"/></td>
            </tr>
            <tr>
                <th>头像图片</th>    <td><input type="file" name="smallpic"></td>
            </tr>
            <tr>
                <th>轮播图一</th>    <td><input type="file" name="firstpic"></td>
            </tr>
            <tr>
                <th>轮播图二</th>    <td><input type="file" name="secondpic"></td>
            </tr>
            <tr>
                <th>轮播图三</th>    <td><input type="file" name="thirdpic"></td>
            </tr>
            <tr>
                <th>轮播图四</th>    <td><input type="file" name="fourthpic"></td>
            </tr>
            <tr>
                <th>驾校资质</th>  <td><input type="text" name = "aptitude"/></td>
            </tr>
            <tr>
                <th>驾校理念</th>  <td><input type="text" name = "idea"/></td>
            </tr>
            <tr>
                <th>驾校详情</th>  <td><input type="text" name = "details"/></td>
            </tr>
            <tr>
                <th>驾校资费</th>  <td><input type="text" name = "money"/></td>
            </tr>
        </table>
        <input type="submit" value="添加"/>
    </form>
    </center>
</body>
</html>
