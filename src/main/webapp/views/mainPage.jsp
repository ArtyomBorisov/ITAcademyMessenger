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
            <c:set value="" var="inf" scope="session"/>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/message';"
                    value="Отправить сообщение"></p>

                <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/chats';"
                    value="Просмотреть сообщения"></p>

                <form action="/Homework_Mk-JD2-88-22-0.0.0/exit">
                    <p><input type="submit" value="Выйти"></p>
                </form>
            </c:when>
            <c:otherwise>
                <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/signUp';"
                    value="Зарегистрироваться"></p>

                <p><input type="button" onclick="location.href='/Homework_Mk-JD2-88-22-0.0.0/signIn';"
                    value="Войти"></p>
            </c:otherwise>
        </c:choose>
    </body>
</html>