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
</head>

<body>
<%@ include file="../fragment/bundle.jsp"%>

<nav><%@ include file="../fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Утверждение заявки</h3></div>
        </article>

        <div class="container">

            <table class="info-table center-element">
                <tr>
                    <td><fmt:message key="ui.admin.firstName"/></td>
                    <td>${requestScope.customer.firstName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.middleName"/></td>
                    <td>${requestScope.customer.middleName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.lastName"/></td>
                    <td>${requestScope.customer.lastName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.city"/></td>
                    <td>${requestScope.customer.city}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.street"/></td>
                    <td>${requestScope.customer.street}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.building"/></td>
                    <td>${requestScope.customer.building}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.apartments"/></td>
                    <td>${requestScope.customer.apartments}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.email"/></td>
                    <td>${requestScope.customer.email}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.setUser.plan"/></td>
                    <td>${requestScope.customer.plan.name}</td>
                </tr>
            </table>


            <form action="${pageContext.request.contextPath}/admin/approveUser.do" method="post">
                <input type="hidden" name="customerToApprove" value="${requestScope.customer.id}">
                <table class="form">
                    <tr>
                        <td><fmt:message key="ui.admin.setUser.contract"/></td>
                        <td><input class="input-field" type="text" name="contract" pattern="[1-9]{1}[0-9]{8}" placeholder="Номер контракта" title="9-значный номер контракта" required></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <button class="button center-element" type="submit">
                                <!-- <fmt:message key="ui.login.enter"/> -->
                                <fmt:message key="ui.admin.setUser.approve"/>
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <br>

        <c:choose>
            <c:when test="${requestScope.contractError eq '1'}">
                <fmt:message key="ui.admin.setUser.error" var="errorMessage"/>
            </c:when>
        </c:choose>

        <div class="center-element center-text error">${errorMessage}</div>
    </div>
</main>

<mct:my-copyright/>

</body>
</html>
