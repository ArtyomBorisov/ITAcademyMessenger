<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Войти</title>
    </head>
    <body>
        <h3>Войти</h3>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                Вы уже вошли.
                <p><input type="button" onclick="location.href='/Messenger/mainPage';"
                    value="На главную"></p>
            </c:when>
            <c:otherwise>
                <c:if test="${!empty inf}">
                    ${inf}
                    <c:set value="" var="inf" scope="session"/>
                </c:if>
                <form action="/Messenger/signIn" method="post">
                    <p>Логин <input type="text" name="login" placeholder="Логин" required></p>
                    <p>Пароль <input type="password" name="password" placeholder="Пароль" required></p>
                    <p><input type="submit"  value="Войти"></p>
                    <p><input type="button" onclick="location.href='/Messenger/mainPage';"
                        value="На главную"></p>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>