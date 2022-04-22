<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Редактирование профиля</title>
    </head>
    <body>
        <h3>Редактировать профиль</h3>
        <form action="/Messenger/editProfile" method="post">
            <p>ФИО <input type="text" name="name" placeholder="ФИО" required></p>
            <p>Дата рождения <input type="date" name="birthday"  placeholder="Дата рождения"></p>
            <p>Новый пароль <input type="password" name="password" placeholder="Пароль" required></p>
            <p><input type="submit"  value="Изменить"></p>
        </form>
        <p><input type="button" onclick="location.href='/Messenger/mainPage';"
            value="На главную"></p>
    </body>
</html>