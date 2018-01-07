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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registr.css">
</head>

<body>
<%@ include file="../fragment/bundle.jsp"%>

<nav><%@ include file="../fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Добавить тарифный план</h3></div>
        </article>

        <div class="container">
            <form action="${pageContext.request.contextPath}/admin/addPlan.do" method="post">
                <table class="form">
                    <tr>
                        <td><fmt:message key="ui.admin.plan.name"/></td>
                        <td><input class="input-field" type="text" name="name" placeholder="Название" title="Текстовое название." required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.admin.plan.description"/></td>
                        <td><textarea class="input-field" name="description" rows="8" placeholder="Описание" title="Текстовое описание" required></textarea></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.admin.plan.downSpeed"/></td>
                        <td><input class="input-field" type="number" name="downloadSpeed" min="1" max="100" placeholder="Входящая скорость" title="Входящая скорость, целое число от 1 до 100." required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.admin.plan.upSpeed"/></td>
                        <td><input class="input-field" type="number" name="uploadSpeed" min="1" max="100" placeholder="Исходящая скорость" title="Исходящая скорость, целое число от 1 до 100." required></td>
                    </tr>

                    <tr>
                        <td><fmt:message key="ui.admin.plan.price"/></td>
                        <td><input class="input-field" type="text" name="price" pattern="([0-9]+\.[0-9]{2})|([1-9]{1}[0-9]*)" placeholder="Стоимость" title="Стоимость в рублях, 2 знака после точкию" required></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <button class="button center-element" type="submit">
                                <fmt:message key="ui.admin.plan.add"/>
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <br>

        <c:choose>
            <c:when test="${requestScope.planError eq '1'}">
                <fmt:message key="ui.admin.plan.error" var="errorMessage"/>
            </c:when>
        </c:choose>

        <div class="center-element center-text error">${errorMessage}</div>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
