<%--
  Created by IntelliJ IDEA.
  User: 12ksa
  Date: 29.04.2018
  Time: 17:14
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
<input type="hidden" name="command" value="org_registration"/>
<br/>Name<br/>
<input type="text" name="org_name" placeholder="name*"/>
<br/>Website<br/>
<input type="url" name="website" placeholder="your site*"/>
<br/>Descr<br/>
<textarea name="description" placeholder="description" required rows="4" cols="50"></textarea>
<br/>Type<br/>
<select name="type">
    <option value="commercial" selected>Commer</option>
    <option value="noncommercial">Noncommer</option>
</select>

<%--<br/><label for="commercial-checkbox"><fmt:message key="content.registration.second.password"/></label>--%>
<%--<input id="commercial-checkbox" type="checkbox" name="is_commercial"/>--%>
<%--<br/><fmt:message key="content.registration.second.password"/><br/>--%>
<%--<input type="password" name="repeat_password" placeholder="password*"/>--%>
<%--<br/><fmt:message key="content.registration.first.password"/><br/>--%>
<%--<input type="password" name="password" placeholder="password*"/>--%>
<br/>
</body>
</html>