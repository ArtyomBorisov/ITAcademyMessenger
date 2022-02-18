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
        <h3>Написать сообщение</h3>
        <form action="/Homework_Mk-JD2-88-22-0.0.0/message" method="post">
            <p>Кому <input type="text" name="loginTo" required></p>
            <p>Сообщение <input type="text" name="messageText" required></p>
            <p><input type="submit"  value="Отправить"></p>
        </form>
        <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/mainPage';"
            value="На главную"></p>
    </body>
</html>