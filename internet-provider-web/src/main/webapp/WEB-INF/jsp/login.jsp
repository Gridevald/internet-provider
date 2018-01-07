<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mct" uri="my-custom-tag" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    <meta charset="utf-8">
    <title>Гигабит</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/pic/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
</head>

<body>
<%@ include file="fragment/bundle.jsp"%>

<nav><%@ include file="fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Вход в личный кабинет</h3></div>
        </article>

        <div class="container">
            <form action="${pageContext.request.contextPath}/main.do" method="post">
                <table class="form">
                    <tr>
                        <td><fmt:message key="ui.login.email"/></td>
                        <td><input class="input-field" type="email" name="email" placeholder="Эл.почта" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.login.password"/></td>
                        <td><input class="input-field" type="password" name="password" placeholder="Пароль" required></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <button class="button center-element" type="submit">
                                <fmt:message key="ui.login.enter"/>
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <br>

        <c:choose>
            <c:when test="${requestScope.loginError eq '1'}">
                <fmt:message key="ui.login.error.1" var="errorMessage"/>
            </c:when>
            <c:when test="${requestScope.loginError eq '2'}">
                <fmt:message key="ui.login.error.2" var="errorMessage"/>
            </c:when>
            <c:when test="${requestScope.loginError eq '3'}">
                <fmt:message key="ui.login.error.3" var="errorMessage"/>
            </c:when>
        </c:choose>

        <div class="center-element center-text error">${errorMessage}</div>

        <br>

        <div class="center-element center-text">
            <fmt:message key="ui.login.regMessage"/> <a href="register.do"><fmt:message key="ui.login.regLink"/></a>
        </div>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
