<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="picRU" scope="page" value="${pageContext.request.contextPath}/pic/ru.png"/>
<c:set var="picEN" scope="page" value="${pageContext.request.contextPath}/pic/en.png"/>
<c:set var="picBE" scope="page" value="${pageContext.request.contextPath}/pic/be.png"/>
<c:set var="url" scope="page" value="${pageContext.request.requestURL}"/>

<c:choose>
    <c:when test="${sessionScope.uiLang eq 'en'}">
        <c:set var="picCurrent" value="${picEN}"/>
    </c:when>
    <c:when test="${sessionScope.uiLang eq 'be'}">
        <c:set var="picCurrent" value="${picBE}"/>
    </c:when>
    <c:otherwise>
        <c:set var="picCurrent" value="${picRU}"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${sessionScope.role eq 'guest'}">
        <fmt:message key="ui.menu.login" var="loginButton"/>
    </c:when>
    <c:otherwise>
        <fmt:message key="ui.menu.office" var="loginButton"/>
    </c:otherwise>
</c:choose>

<ul class="menu">
    <li><a href="${pageContext.request.contextPath}/home.do"><fmt:message key="ui.menu.home"/></a></li>
    <li><a href="${pageContext.request.contextPath}/plans.do"><fmt:message key="ui.menu.plans"/></a></li>
    
    <li class="allign-right"><a href=#><img src="${picCurrent}" alt="language"></a>
        <ul class="submenu">
            <li>
                <form action="${pageContext.request.contextPath}/changeLang.do" method="post">
                    <input type="hidden" name="langToSet" value="ru">
                    <input type="hidden" name="previousPage" value="${url}">
                    <input type="image" src="${picRU}" value="русский">
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/changeLang.do" method="post">
                    <input type="hidden" name="langToSet" value="en">
                    <input type="hidden" name="previousPage" value="${url}">
                    <input type="image" src="${picEN}" value="english">
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/changeLang.do" method="post">
                    <input type="hidden" name="langToSet" value="be">
                    <input type="hidden" name="previousPage" value="${url}">
                    <input type="image" src="${picBE}" value="беларуски">
                </form>
            </li>
        </ul>
    </li>

    <li class="allign-right"><a href="${pageContext.request.contextPath}/login.do">${pageScope.loginButton}</a></li>
</ul>
