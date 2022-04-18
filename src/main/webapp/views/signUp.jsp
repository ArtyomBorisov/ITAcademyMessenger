<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Регистрация</title>
    </head>
    <body>
        <form action="/Messenger/signUp" method="post">
            <c:choose>
                <c:when test="${user != null}">
                    Вы уже зарегистрированы!
                    <p><input type="button" onclick="location.href='/Messenger/mainPage';"
                        value="На главную"></p>
                </c:when>
                <c:otherwise>
                    <c:if test="${!empty inf}">
                        ${inf}
                        <c:set value="" var="inf" scope="request"/>
                    </c:if>
                    <p>Пройдите регистрацию.</p>
                    <p>Логин <input type="text" name="login" placeholder="Логин" required></p>
                    <p>Пароль <input type="password" name="password" placeholder="Пароль" required></p>
                    <p>ФИО <input type="text" name="name"  placeholder="ФИО" required></p>
                    <p>Дата рождения <input type="date" name="birthday"  placeholder="Дата рождения"></p>
                    <p><input type="submit"  value="Зарегистрироваться"></p>
                    <p><input type="button" onclick="location.href='/Messenger/mainPage';"
                        value="На главную"></p>
                </c:otherwise>
            </c:choose>
        </form>
    </body>
</html>