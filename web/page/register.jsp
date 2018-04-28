<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 4/25/2018
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="registration_content"/>
<html>
<head>
</head>
<body>
<input type="hidden" name="command" value="registration"/>
<br/><fmt:message key="content.registration.name"/><br/>
<input type="text" name="first_name" placeholder="name*"/>
<br/><fmt:message key="content.registration.surname"/><br/>
<input type="text" name="last_name" placeholder="surname*"/>
<br/><fmt:message key="content.registration.email"/><br/>
<input type="email" name="email" placeholder="default@example.com" required/>
<br/><fmt:message key="content.registration.second.password"/><br/>
<input type="password" name="repeat_password" placeholder="password*"/>
<br/><fmt:message key="content.registration.first.password"/><br/>
<input type="password" name="password" placeholder="password*"/>
<br/>
</body>
</html>
