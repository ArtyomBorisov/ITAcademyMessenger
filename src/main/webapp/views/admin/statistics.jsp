<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Статистика</title>
    </head>
    <body>
         <h3>Статистика</h3>
         <table border="1">
            <tbody>
                <c:forEach items="${requestScope.stats}" var="stat">
                    <tr>
                        <td>${stat.key}</td>
                        <td>${stat.value}</td>
                    </tr>
                </c:forEach>
            </tbody>
         </table>
         <p><input type="button" onclick="location.href='/Messenger/mainPage';"
            value="На главную"></p>
    </body>
</html>