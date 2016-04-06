<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 2016/3/14
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新用户分组</title>
</head>
<body>
    <form>
        <center>
            <table>
                <%
                    String openId = request.getAttribute("openId").toString();
                    request.setAttribute("openId",openId);
                %>


                <tr>
                    <th>新分组编号</th><td><input type="text" name="groupId"/></td>
                </tr>
            </table>
        </center>
    </form>
</body>
</html>
