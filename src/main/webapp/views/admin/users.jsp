<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Пользователи</title>
    </head>
    <body>
         <table border="1">
             <tr>
                 <th>Логин</th>
                 <th>ФИО</th>
                 <th>Дата рождения</th>
                 <th>Дата регистрации</th>
                 <th>Дата последнего обновления</th>
             </tr>
             <c:forEach var="user" items="${requestScope.users}">
                 <tr>
                    <th>${user.login}</th>
                    <th>${user.name}</th>
                    <th><tags:localDate date="${user.birthday}"/></th>
                    <th><tags:localDateTime date="${user.registration}"/></th>
                    <th><tags:localDateTime date="${user.lastUpdate}"/></th>
                 </tr>
             </c:forEach>
         </table>

         <p><input type="button" onclick="location.href='/Messenger/mainPage';"
            value="На главную"></p>
    </body>
</html>