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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
</head>

<body>
<%@ include file="fragment/bundle.jsp"%>

<nav><%@ include file="fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Оформление заказа</h3></div>
        </article>

        <div class="container">
            <form action="${pageContext.request.contextPath}/makeOrder.do" method="post" name="registerForm" id="registerFormId" autocomplete="off">
                <table class="form">

                    <tr>
                        <td colspan="2">
                            <div class="center-text">
                                <select class="input-field" name="selectPlan">
                                    <c:forEach var="plan" items="${requestScope.planList}">
                                        <option value="${plan.id}">${plan.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.firstName"/></td>
                        <td><input class="input-field" type="text" name="firstName" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{1,}" placeholder="Имя" title="Допустимы только буквы, первая буква в верхнем регистре, минимальная длина 2 буквы" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.middleName"/></td>
                        <td><input class="input-field" type="text" name="middleName" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{3,}" placeholder="Отчество" title="Допустимы только буквы, первая буква в верхнем регистре, минимальная длина 3 буквы" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.lastName"/></td>
                        <td><input class="input-field" type="text" name="lastName" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{1,}" placeholder="Фамилия" title="Допустимы только буквы, первая буква в верхнем регистре, минимальная длина 2 буквы" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.city"/></td>
                        <td><input class="input-field" type="text" name="city" pattern="[A-ZА-ЯЁ]{1}[a-zа-яё]{1,}" placeholder="Город" title="Допустимы только буквы, первая буква в верхнем регистре, минимальная длина 2 буквы" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.street"/></td>
                        <td><input class="input-field" type="text" name="street" pattern="[A-ZА-ЯЁ]{1}[A-ZА-Яa-zа-яё\s]{4,}" placeholder="Улица" title="Допустимы только буквы, первая буква в верхнем регистре, минимальная длина 5 букв" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.building"/></td>
                        <td><input class="input-field" type="text" name="building" pattern="[1-9]{1}[1-0\-/]*[A-Za-zА-Яа-яЁё]{1}" placeholder="Дом" title="Допустим номер дома со знаками '-' и '/' и буквенным постфиксом" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.apartments"/></td>
                        <td><input class="input-field" type="number" name="apartments" min="1" max="5000" placeholder="Квартира" title="Номер квартиры от 1 до 5000" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.email"/></td>
                        <td><input class="input-field" type="email" name="email" pattern="[^@]+@[^@]+\.[^@]+" placeholder="Электронная почта" title="Электронный почта" required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.password"/></td>
                        <td><input class="input-field" id="pass1" type="password" name="passwordF" placeholder="Пароль" title="Минимум 6 символов." required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.register.repeatPassword"/></td>
                        <td><input class="input-field" id="pass2" type="password" name="passwordS" placeholder="Повторите пароль" title="Минимум 6 символов." required></td>
                    </tr>

                    <fmt:message key="ui.register.passError" var="passErrorMessage"/>
                    <input type="hidden" name="passErrorHidden" value="${passErrorMessage}" id="passErrorHiddenId">

                    <tr>
                        <td colspan="2">
                            <button class="button center-element" type="submit" onclick="return checkform();">
                                <fmt:message key="ui.register.submitButton"/>
                            </button>
                        </td>
                    </tr>

                </table>
            </form>
        </div>

        <br>

        <c:choose>
            <c:when test="${requestScope.registerError eq '1'}">
                <fmt:message key="ui.register.error.1" var="errorMessage"/>
            </c:when>
            <c:when test="${requestScope.registerError eq '2'}">
                <fmt:message key="ui.register.error.2" var="errorMessage"/>
            </c:when>
        </c:choose>

        <div class="error center-element center-text" id="passErrorBlock">${errorMessage}</div>

    </div>
</main>

<mct:my-copyright/>

</body>
</html>
