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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/pic/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
</head>

<body>
<%@ include file="fragment/bundle.jsp"%>

<nav><%@ include file="fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Интернет-провайдер Гигабит приветствует Вас!</h3></div>
            <div class="center-text">
                <img class="pic" src="${pageContext.request.contextPath}/pic/home_page_pic.jpg">
            </div>
            <p>Интернет-провайдер Гигабит - это Ваш надёжный поставщик услуг связи интернет. Мы предлагаем вам стабильное соединение 24/7 с указанной скоростью, лучшие цены, управление аккаунтом в личном кабинете и приветливый персонал. У нас каждый пользователь найдёт тариф, подходящий именно ему. </p>
        </article>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
