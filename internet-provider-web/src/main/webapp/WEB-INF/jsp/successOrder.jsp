<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</head>

<body>
<%@ include file="fragment/bundle.jsp"%>

<nav><%@ include file="fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Заказ отправлен. Ждите подтверждение.</h3></div>
        </article>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
