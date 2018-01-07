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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
</head>

<body>
<%@ include file="../fragment/bundle.jsp"%>

<nav><%@ include file="../fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Пополнение счёта</h3></div>
        </article>

        <br>

        <div class="container">
            <form action="${pageContext.request.contextPath}/user/makePayment.do" method="post">
                <table class="form">
                    <tr>
                        <td><fmt:message key="ui.user.sum"/></td>
                        <td><input class="input-field" type="text" name="sum" pattern="([0-9]+\.[0-9]{2})|([1-9]{1}[0-9]*)" placeholder="Сумма" title="Сумма в рублях" required></td>
                    </tr>

                    <tr>
                        <td colspan="2" class="center-text"><fmt:message key="ui.user.cardInfo"/></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.user.cardNumber"/></td>
                        <td><input class="input-field" type="text" name="cardNumber" pattern="[0-9]{16}" placeholder="Номер карты" title="16-значный номер карты" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.user.validity"/></td>
                        <td><input class="input-field" type="number" name="cardMonth" min="1" max="12" placeholder="1" title="Номер месяца от 1 до 12">
                        <input class="input-field" type="number" name="cardYear" min="2018" max="2100" placeholder="2018" title="Год от 2018 до 2100"></td>
                    </tr>

                    <tr>
                        <td>CVC2/CVV2</td>
                        <td><input class="input-field" type="text" name="cardSecurity" pattern="[0-9]{3}"" placeholder="123" title="Номер безопасности"></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <button class="button center-element" type="submit">
                                <fmt:message key="ui.user.doPayment"/>
                            </button>
                        </td>
                    </tr>
                </table>
            </form>

            <br>

            <div class="error center-element center-text">
                <fmt:message key="ui.user.sumError" var="errorMessage"/>
                <c:choose>
                    <c:when test="${requestScope.sumError eq '1'}">
                        ${errorMessage}
                    </c:when>
                </c:choose>
            </div>

        </div>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
