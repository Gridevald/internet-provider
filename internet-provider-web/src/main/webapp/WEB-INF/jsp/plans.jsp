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
</head>

<body>
<%@ include file="fragment/bundle.jsp"%>

<nav><%@ include file="fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Тарифные планы.</h3></div>
            <p>У нас каждый пользователь найдёт тариф, подходящий именно ему. Наши тарифные планы не лимитированы по объёму скачиваемого и выгружаемого трафика, так что Вам нужно лишь выбрать подходящую скорость и стоимость.</p>
        </article>

        <br>

        <div class="my-table">
            <table>
                <thead>
                    <tr>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Входящая скорость,<br>Мбит/с</th>
                        <th>Исходящая скорость,<br>Мбит/с</th>
                        <th>Стоимость в месяц,<br>рублей</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="plan" items="${requestScope.planList}">
                        <tr>
                            <td>${plan.name}</td>
                            <td>${plan.description}</td>
                            <td data-label="Входящая скорость, Мбит/с">${plan.downloadSpeed}</td>
                            <td data-label="Исходящая скорость, Мбит/с">${plan.uploadSpeed}</td>
                            <td data-label="Стоимость в месяц, рублей">${plan.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <br>

       <form action="${pageContext.request.contextPath}/register.do" method="post">
           <button class="button center-element" type="submit"><fmt:message key="ui.plans.order"/></button>
       </form>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
