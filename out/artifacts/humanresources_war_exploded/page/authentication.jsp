<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 28.03.2018
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="authentication_content"/>
<html>
<head><title><fmt:message key="content.authentication.title"/></title></head>
<body>
<ctg:role-time role="${role}"/>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="toRegistration"/>
    <input type="button" value="<fmt:message key="content.authentication.registration"/>"/>
</form>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="authentication"/>
    <fmt:message key="content.authentication.email"/><br/>
    <input type="text" name="login" value=""/>
    <fmt:message key="content.authentication.password"/><br/>
    <input type="password" name="pass" value="" placeholder="Password*"/>
    <input type="submit" value="<fmt:message key="content.authentication.continue"/>"/>
</form>
<br/>
${error}
<br/>
${user}
<br/>
</body>
</html>
