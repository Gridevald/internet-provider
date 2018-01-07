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
                        <button class="button tablink" onclick="opentab(event, 'traffic')"><fmt:message key="ui.user.traffic"/></button>
                        <button class="button tablink" onclick="opentab(event, 'payments')"><fmt:message key="ui.user.payments"/></button>
                    </div>
                </td>
            </tr>
        </table>

        <br>

        <!-- user info -->
        <div id="info" class="tabcontent">
            <table class="info-table center-element">
                <tr>
                    <td><fmt:message key="ui.user.firstName"/></td>
                    <td>${sessionScope.person.firstName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.middleName"/></td>
                    <td>${sessionScope.person.middleName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.lastName"/></td>
                    <td>${sessionScope.person.lastName}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.contract"/></td>
                    <td>${sessionScope.person.contract}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.plan"/></td>
                    <td>${sessionScope.person.plan.name}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.balance"/></td>
                    <td>${sessionScope.person.balance}</td>
                </tr>
                <tr>
                    <td><fmt:message key="ui.user.blocking"/></td>
                    <c:choose>
                        <c:when test="${sessionScope.person.isBlocked eq '1'}">
                            <td>Заблокирован</td>
                        </c:when>
                        <c:otherwise>
                            <td>Не блокирован</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>

            <br><br><br>

            <table class="center-element">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/user/setPayment.do" method="post" id="pay">
                        </form>
  
                        <form action="${pageContext.request.contextPath}/user/choosePlan.do" method="post" id="changePlan">
                        </form>
  
                        <form action="${pageContext.request.contextPath}/logout.do" method="post" id="logout">
                        </form>
         
                        <div class="btn-group">
                            <button type="submit" form="pay" class="button"><fmt:message key="ui.user.pay"/></button>
                            <button type="submit" form="changePlan" class="button"><fmt:message key="ui.user.changePlan"/></button>
                            <button type="submit" form="logout" class="button"><fmt:message key="ui.main.logout"/></button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <!-- traffic -->
        <div id="traffic" class="tabcontent">
            <div class="my-table">
                <table>
                    <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Загруженао, Мб</th>
                            <th>Выгружено, Мб</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="traffic" items="${sessionScope.person.trafficList}">
                            <tr>
                                <td><fmt:formatDate value="${traffic.date}" pattern="yyyy-MM-dd"/></td>
                                <td data-label="Загруженао, Мб">${traffic.downloaded}</td>
                                <td data-label="Выгружено, Мб">${traffic.uploaded}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- payments -->
        <div id="payments" class="tabcontent">
            <div class="my-table">
                <table>
                    <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Сумма, рублей</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="payment" items="${sessionScope.person.paymentList}">
                            <tr>
                                <td><fmt:formatDate value="${payment.date}" pattern="yyyy-MM-dd"/></td>
                                <td>${payment.sum}</td>
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
