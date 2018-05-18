<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 13.05.2018
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
</head>
<body>
<div class="container-fluid form-group">
    <form class="row" name="filter" method="POST" action="controller">
        <input class="form-control" type="hidden" name="command" value="request_filter"/>
        <div class="col-3">
            <label for="firstSelect"><fmt:message key="content.vacancy.sort.field"/></label>
            <select name="sort_col" id="firstSelect" class="form-control ">
                <option value="sort_by_empty_column"></option>
                <option value="sort_by_vac_name">Vac name</option>
                <option value="sort_by_user_name">User name</option>
            </select>
        </div>
        <div class="col-3">
            <label for="secondSelect"><fmt:message key="content.vacancy.sort.type"/></label>
            <select name="sort_type" id="secondSelect" class="form-control ">
                <option value="empty"></option>
                <option value="decrease"><fmt:message key="content.vacancy.sort.decrease"/></option>
                <option value="increase"><fmt:message key="content.vacancy.sort.increase"/></option>
            </select>
        </div>

        <div class="col-5">
            <label for="searchInput"><fmt:message key="content.vacancy.search"/></label>
            <input class="form-control " id="searchInput" type="text" name="search_field"
                   placeholder="<fmt:message key="content.vacancy.search"/>"/>
        </div>
        <div class="col-1">
            <label for="searchButton">&#160;</label>
            <input id="searchButton" class="btn btn-primary float-left" type="submit"
                   value="<fmt:message key="content.vacancy.search"/>"/>
        </div>
    </form>
</div>

<table class="table table-hover">
    <tbody id="vacancy-table">
    <tr>
        <th>!Вакансия</th>
        <th>!Пользователь</th>
        <th>!Больше</th>
    </tr>

    <c:forEach items="${requestScope.request_list}" var="request">
        <tr>
            <td>${request.jobVacancy.name}</td>
            <td>${request.user.email}</td>
            <c:choose>
                <c:when test="${sessionScope.role == 'director'}">
                    <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target="#vacancy-${request.id}-modal">
                        <fmt:message key="content.add.request"/>
                    </button>
                    <!-- The Modal -->
                    <div class="modal fade" id="vacancy-${request.id}-modal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">
                                        <%--TODO: add local content--%>Modal Heading</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;
                                </button>
                            </div>
                            <form name="requestForm" method="POST" action="controller">
                                <input class="form-control" type="hidden" name="request_id"
                                       value="${request.id}"/>
                                <!-- Modal body -->
                                <div class="modal-body">
                                        <%--TODO: add local content--%>
                                    <h4>Requirements:</h4>
                                    <p>${vacancy.requirement}</p>
                                    <a href="${vacancy.organization.website}">Go to their website</a>
                                    <br>
                                    <input class="form-control" type="hidden" name="command"
                                           value="request_registration"/>
                                    <label for="vacancy-${vacancy.id}-org-description">Descr</label>>
                                    <textarea class="form-control"
                                              id="vacancy-${vacancy.id}-org-description" name="resume"
                                              placeholder="Resume*" required rows="4"
                                              cols="50"></textarea>
                                        <%--TODO: change form source--%>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input class="btn btn-primary float-left" type="submit"
                                           value="<fmt:message key="content.button.submit"/>"/>
                                    <button type="button" class="btn btn-secondary"
                                            data-dismiss="modal">
                                            <%--TODO: add local content--%>Close
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>
                </c:when>
                <c:when test="${sessionScope.role == 'admin'}">
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#vacancy-${vacancy.id}-modal">
                            <fmt:message key="content.add.request"/>
                        </button>
                        <div class="modal fade" id="vacancy-${vacancy.id}-modal">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">
                                                <%--TODO: add local content--%>Modal Heading</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;
                                        </button>
                                    </div>
                                    <input class="form-control" type="hidden" name="command"
                                           value="confirm_vacancy"/>
                                    <input class="form-control" type="hidden" name="vacancy_id"
                                           value="${vacancy.id}"/>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                            <%--TODO: add local content--%>
                                        <h4>Requirements:</h4>
                                        <p>${vacancy.requirement}</p>
                                        <a href="${vacancy.organization.website}">Go to their website</a>
                                        <br>
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <form name="confirmForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="confirm_vacancy">
                                            <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                                            <input class="btn btn-primary float-left" type="submit"
                                                   value="Добавить вакансию"/>
                                        </form>

                                        <form name="confirmForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="delete_vacancy">
                                            <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                                            <input class="btn btn-primary float-left" type="submit"
                                                   value="Удалить вакансию"/>
                                        </form>

                                        <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">
                                                <%--TODO: add local content--%>Закрыть
                                        </button>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
