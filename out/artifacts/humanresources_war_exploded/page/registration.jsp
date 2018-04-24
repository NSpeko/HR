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
<fmt:setBundle basename="registration_content"/>
<html><head><title>Login</title></head>
<body>
<ctg:role-time role="${role}"/>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="registration"/>
    <br><fmt:message key="content.registration.email"/>
    <input type="text" name="email" placeholder="email*" required/>
    <br><fmt:message key="content.registration.first.password"/>
    <input type="password" name="password" placeholder="password*"/>
    <br><fmt:message key="content.registration.second.password"/>
    <input type="password" name="repeatpassword" placeholder="password*"/>
    <br><fmt:message key="content.registration.name"/>
    <input type="text" name="firstname" placeholder="name*"/>
    <br><fmt:message key="content.registration.surname"/>
    <input type="text" name="lastname" placeholder="surname*"/>
    <input type="submit" value="<fmt:message key="content.registration.continue"/>"/>
</form>
<br/>
${error}
<br/>
</body></html>
