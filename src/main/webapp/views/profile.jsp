<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Профиль</title>
    </head>
    <body>
            <h3>Профиль</h3>
            <p>ФИО: ${user.name}</p>
            <p>Логин: ${user.login}</p>
            <p>Дата рождения: <tags:localDate date="${user.birthday}"/></p>
            <p>Дата регистрации: <tags:localDateTime date="${user.registration}"/></p>

            <p><input type="button" onclick="location.href='/Messenger/editProfile';" value="Изменить профиль"></p>
            <form action="/Messenger/deleteAccount" method="post">
                <p><input type="submit" value="Удалить аккаунт"></p>
            </form>
            <p><input type="button" onclick="location.href='/Messenger/mainPage';" value="На главную"></p>
    </body>
</html>