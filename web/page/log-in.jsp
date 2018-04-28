<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 4/25/2018
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="authentication_content"/>
<html>
<head>
</head>
<body>
<input type="hidden" name="command" value="authentication"/>
<br/><fmt:message key="content.authentication.email"/><br/>
<input type="email" name="login" value="" placeholder="default@example.com"/>
<br/><fmt:message key="content.authentication.password"/><br/>
<input type="password" name="password" value="" placeholder="password"/>
<br/>
</body>
</html>
