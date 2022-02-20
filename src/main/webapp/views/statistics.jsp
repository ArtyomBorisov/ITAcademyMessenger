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
             <tr>
                 <th>Количество активных сессий</th>
                 <th>${sessionScope.counter}</th>
             </tr>
             <tr>
                 <th>Количество зарегистрированных пользователей</th>
                 <th>${sessionScope.users}</th>
             </tr>
             <tr>
                 <th>Количество отправленных сообщений</th>
                 <th>${sessionScope.messages}</th>
             </tr>
         </table>

         <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/mainPage';"
            value="На главную"></p>
    </body>
</html>