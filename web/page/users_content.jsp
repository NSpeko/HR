<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 27.05.2018
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
</head>
<body>
<form name="user_control" method="POST" action="controller">
    <div class="container-fluid form-group">
        <input type="hidden" name="command" value="delete_user">
        <input class="user_control_button" type="submit" value="<fmt:message key="content.button.delete"/>"/>
        <input type="submit" value="<fmt:message key="content.button.rise.admin"/>"
               formaction="/controller?command=delete_user"/>
    </div>
    <table class="table table-hover">
        <tbody id="user-table">
        <tr>
            <th></th>
            <th><fmt:message key="content.user.email"/></th>
            <th><fmt:message key="content.user.first.name"/></th>
            <th><fmt:message key="content.user.last.name"/></th>
            <th><fmt:message key="content.user.role"/></th>
        </tr>
        </tbody>
        <c:forEach items="${requestScope.user_list}" var="user">
            <tr>
                <td><label>
                    <input type="checkbox" name="user_id" value="1">
                </label></td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
        <tr>
            <td><label>
                <input type="checkbox" name="user_id" value="1">
            </label></td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
        </tr>
        <tr>
            <td><label>
                <input type="checkbox" name="user_id" value="2">
            </label></td>
            <td>2</td>
            <td>2</td>
            <td>2</td>
            <td>2</td>
        </tr>
    </table>
</form>
</body>
</html>
