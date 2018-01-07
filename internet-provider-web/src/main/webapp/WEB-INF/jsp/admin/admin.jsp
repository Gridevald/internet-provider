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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tab-info-table.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">
</head>

<body>
<%@ include file="../fragment/bundle.jsp"%>

<nav><%@ include file="../fragment/header.jsp"%></nav>

<main>
    <div class="content">
        <article>
            <div class="center-text"><h3>Личный кабинет</h3></div>
        </article>

        <table class="center-element">
            <tr>
                <td>
                    <div class="btn-group">
                        <button class="button tablink" onclick="opentab(event, 'info')" id="defaultOpen"><fmt:message key="ui.main.info"/></button>
                        <button class="button tablink" onclick="opentab(event, 'users')"><fmt:message key="ui.admin.users"/></button>
                        <button class="button tablink" onclick="opentab(event, 'plans')"><fmt:message key="ui.admin.plans"/></button>
                        <button class="button tablink" onclick="opentab(event, 'customers')"><fmt:message key="ui.admin.customers"/></button>
                    </div>
                </td>
            </tr>
        </table>

        <br>

        <!-- admin info -->
        <div id="info" class="tabcontent">
            <table class="info-table center-element">
                <tr>
                    <td><fmt:message key="ui.admin.firstName"/></td>
                    <td>${sessionScope.person.firstName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.middleName"/></td>
                    <td>${sessionScope.person.middleName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.lastName"/></td>
                    <td>${sessionScope.person.lastName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.admin.personnelNumber"/></td>
                    <td>${sessionScope.person.personnelNumber}</td>
                </tr>
            </table>

            <br>

            <table class="center-element">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/logout.do" method="post">
                            <button type="submit" class="button"><fmt:message key="ui.main.logout"/></button>
                        </form>
                    </td>
                </tr>
            </table>
        </div>

        <!-- users -->
        <div id="users" class="tabcontent">
            <div class="my-table">
                <table>
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>Тарифный план</th>
                            <th>Баланс</th>
                            <th>Блокировка</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="user" items="${requestScope.userList}">
                            <tr>
                                <td>${user.firstName} ${user.middleName} ${user.lastName}</td>
                                <td>${user.plan.name}</td>
                                <td>${user.balance}</td>
                                <c:choose>
                                    <c:when test="${user.isBlocked eq '1'}">
                                        <td>Заблокирован</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Не блокирован</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin/blockUnblockUser.do" method="post">
                                        <input type="hidden" name="userToBlock" value="${user.id}">
                                        <input type="hidden" name="block" value="1">
                                        <c:choose>
                                            <c:when test="${user.isBlocked eq '1'}">
                                                <button type="submit" class="button center-element" disabled><fmt:message key="ui.admin.block"/></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="button center-element"><fmt:message key="ui.admin.block"/></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>

                                    <form action="${pageContext.request.contextPath}/admin/blockUnblockUser.do" method="post">
                                        <input type="hidden" name="userToBlock" value="${user.id}">
                                        <input type="hidden" name="block" value="0">
                                        <c:choose>
                                            <c:when test="${user.isBlocked eq '1'}">
                                                <button type="submit" class="button center-element"><fmt:message key="ui.admin.unblock"/></button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="button center-element" disabled><fmt:message key="ui.admin.unblock"/></button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- plans -->
        <div id="plans" class="tabcontent">
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

                <br>

                <form action="${pageContext.request.contextPath}/admin/setPlan.do" method="post">
                    <button class="button center-element" type="submit"><fmt:message key="ui.admin.setPlan"/></button>
                </form>
            </div>
        </div>

        <!-- customers -->
        <div id="customers" class="tabcontent">
            <div class="my-table">
                <table>
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>Адрес</th>
                            <th>Тарифный план</th>
                            <th>Эл.почта</th>
                            <th></th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="customer" items="${requestScope.customerList}">
                            <tr>
                                <td>${customer.firstName} ${customer.middleName} ${customer.lastName}</td>
                                <td>${customer.city}, ${customer.street} ${customer.building} - ${customer.apartments}</td>
                                <td>${customer.plan.name}</td>
                                <td>${customer.email}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin/setUser.do" method="post">
                                        <input type="hidden" name="customerToApprove" value="${customer.id}">
                                        <button type="submit" class="button center-element"><fmt:message key="ui.admin.setUser.approve"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</main>

<mct:my-copyright/>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabs.js"></script>

</html>
