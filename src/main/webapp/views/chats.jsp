<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Сообщения</title>
    </head>
    <body>
        <h3>Полученные сообщения</h3>
         <table border="1">
             <tr>
                 <th>Дата и время отправки</th>
                 <th>От кого</th>
                 <th>Текст</th>
             </tr>
             <c:forEach var="item" items="${sessionScope.messages}">
                 <tr>
                    <th>${item.timeSending}</th>
                    <th>${item.message.userFrom}</th>
                    <th>${item.message.message}</th>
                 </tr>
             </c:forEach>
         </table>

         <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/mainPage';"
            value="На главную"></p>
    </body>
</html>