<%@ page language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Главная страница</title>
    </head>
    <body>
        <h3>Мессенджер</h3>
        <c:if test="${!empty inf}">
            ${inf}
            <c:set value="" var="inf" scope="request"/>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <p><input type="button" onclick="location.href='/Messenger/message';"
                    value="Отправить сообщение"></p>

                <p><input type="button" onclick="location.href='/Messenger/chats';"
                    value="Просмотреть сообщения"></p>

                <p><input type="button" onclick="location.href='/Messenger/statistics';"
                                    value="Статистика"></p>

                <form action="/Messenger/exit">
                    <p><input type="submit" value="Выйти"></p>
                </form>
            </c:when>
            <c:otherwise>
                <p><input type="button" onclick="location.href='/Messenger/statistics';"
                    value="Статистика"></p>

                <p><input type="button" onclick="location.href='/Messenger/signUp';"
                    value="Зарегистрироваться"></p>

                <p><input type="button" onclick="location.href='/Messenger/signIn';"
                    value="Войти"></p>
            </c:otherwise>
        </c:choose>
    </body>
</html>