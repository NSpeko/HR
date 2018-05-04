<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 04.05.2018
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input class="form-control" type="hidden" name="command" value="vacancy_registration"/>
    <label for="org-site">Name</label>
    <input class="form-control" id="org-site" type="text" name="vacancy_name" placeholder="Vacancy name*"/>
    <label for="org-description">Requirement</label>
    <textarea class="form-control" id="org-description" name="vacancy_requirement" placeholder="requirement" required
              rows="4" cols="50"></textarea><br/>
    <input type="submit" class="btn btn-primary" value="submit"/>
</form>
</body>
</html>
