<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 20.04.2018
  Time: 18:21
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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="../style/style.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script src="../javascript/main-script.js"></script>
    <title><fmt:message key="content.static.main.title"/></title>
</head>
<body>
<div id="notifications-wrapper"><c:import url="component.jsp"/></div>
<div class="modal fade text-dark" id="log-in-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.button.user.sign.up"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="loginForm" method="POST" action="controller">
                <div class="modal-body ">
                    <c:import url="log-in.jsp"/>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.button.submit"/>"/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <fmt:message key="content.button.cancel"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- The Modal -->
<div class="modal fade text-dark" id="logo-site-info">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    Описание
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body ">
                Очень хорошое место для Сереги
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">
                    <fmt:message key="content.button.cancel"/>
                </button>
            </div>

        </div>
    </div>
</div>
<div class="modal fade text-dark" id="sign-up-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.modal.header.sign.up"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="loginForm" method="POST" action="controller">
                <div class="modal-body ">
                    <div id="sign-up-as-user-form"><c:import url="register_user.jsp"/></div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.button.submit"/>"/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <fmt:message key="content.button.cancel"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
<div class="modal fade text-dark" id="user-profile-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <fmt:message key="content.modal.header.profile"/>
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="profileForm" method="POST" action="controller">
                <div class="modal-body ">
                    <%--<label for="user-profile-first-name"><fmt:message key="content.user.first.name"/></label>--%>
                    <%--<input class="form-control" id="user-profile-first-name" type="text" name="user_profile_first_name" value=" ${sessionScope.user_info.firstName}"/>--%>
                    <%--<label for="user-profile-last-name"><fmt:message key="content.user.last.name"/></label>>--%>
                    <%--<input class="form-control" id="user-profile-last-name" type="text" name="user_profile_last_name" value=" ${sessionScope.user_info.lastName}"/>--%>
                    <%--<label for="user-profile-email"><fmt:message key="content.user.email"/></label>>--%>
                    <%--<input class="form-control" id="user-profile-email" type="email" name="user_profile_email" value=" ${sessionScope.user_info.email}"/>--%>
                    <p><fmt:message key="content.user.first.name"/> - ${sessionScope.user_info.firstName}</p>
                    <p><fmt:message key="content.user.last.name"/> - ${sessionScope.user_info.lastName}</p>
                    <p><fmt:message key="content.user.email"/> - ${sessionScope.user_info.email}</p>
                    <p><fmt:message key="content.org.name"/> - ${sessionScope.user_org_info.name}</p>
                        <p><fmt:message key="content.org.website"/> - <a href="${sessionScope.user_org_info.website}">Website</a></p>
                    <c:if test="${sessionScope.user_org_info == null}">
                        <button class="btn btn-primary " data-toggle="collapse" data-target="#org-register-form">
                            <fmt:message key="content.button.add.organization"/>
                        </button>
                    </c:if>

                    <div id="org-register-form" class="collapse">
                        <div id="sign-up-org-form"><c:import url="register_org.jsp"/></div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input class="btn btn-primary float-left" type="submit"
                           value="<fmt:message key="content.button.submit"/>"/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <fmt:message key="content.button.cancel"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
<div class="full-wrapper">
    <header class="container-fluid bg-dark text-white fixed-top p-2">


        <c:if test="${requestScope.info != null}">
            <script>
                alert("<fmt:message key="${requestScope.info}"/>");
            </script>
        </c:if>


        <div class="container ">
            <div class="row">
                <div class="col-8">
                    <nav>
                        <ul class="nav nav-pills ">
                            <li class="nav-item">
                                <a data-toggle="modal" data-target="#logo-site-info" href="#" class="nav-link"><img
                                        style="width:2rem;" src="../resources/logo.jpg"></a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col clearfix">
                    <div class="float-right">
                        <c:choose>
                            <c:when test="${sessionScope.role==null}">
                                <button id="log-in-button" class="btn btn-dark " data-toggle="modal"
                                        data-target="#log-in-modal">
                                    <fmt:message key="content.button.user.log.in"/>
                                </button>
                                <!-- Button to Open the Modal -->
                                <button id="sign-up-button" class="btn btn-primary " data-toggle="modal"
                                        data-target="#sign-up-modal">
                                    <fmt:message key="content.button.user.sign.up"/>
                                </button>
                            </c:when>
                            <c:when test="${sessionScope.role!= null}">
                                <button class="btn btn-dark " data-toggle="modal" data-target="#user-profile-modal">
                                    <fmt:message key="content.button.user.profile"/>
                                </button>
                                <!-- Button to Open the Modal -->
                                <a href="${pageContext.request.contextPath}/controller?command=log_out">
                                    <button class="btn btn-primary " name="command" value="log_out">
                                        <fmt:message key="content.button.user.log.out"/>
                                    </button>
                                </a>
                            </c:when>
                        </c:choose>
                        <div class="dropdown btn">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                Lang
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_russian_locale"><img
                                        src="http://konsomejona.github.io/OctoMouse/images/flag_ru.png"/>Русский</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_english_locale"><img
                                        src="https://whitehousefoods.com/wp-content/themes/whitehouse/img/flag.gif">English</a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/controller?command=est_belorussian_locale"><img
                                        src="https://lipis.github.io/flag-icon-css/flags/4x3/by.svg">Беларускі</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="wrapper container">
        <section class="jumbotron">
            <h1><fmt:message key="content.main.slogan.first"/></h1>
            <p><fmt:message key="content.main.slogan.second"/></p>
        </section>
        <div class="container-fluid form-group">
            <form class="row" name="filter" method="POST" action="controller">
                <input class="form-control" type="hidden" name="command" value="vacancy_filter"/>
                <div class="col-3">
                    <label for="firstSelect">Select 1</label>
                    <select name="sort_col" id="firstSelect" class="form-control ">
                        <option value="sort_by_empty_column"></option>
                        <option value="sort_by_name"><fmt:message key="content.vacancy.name"/></option>
                        <option value="sort_by_date"><fmt:message key="content.vacancy.date"/></option>
                        <option value="sort_by_organization"><fmt:message key="content.org.name"/></option>
                    </select>
                </div>
                <div class="col-3">
                    <label for="secondSelect">Select 2</label>
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

        <%--Replace it--%>

        <table class="table table-hover">
            <tbody id="vacancy-table">
            <tr>
                <th><fmt:message key="content.vacancy.name"/></th>
                <th><fmt:message key="content.vacancy.date"/></th>
                <th><fmt:message key="content.vacancy.requirement"/></th>
                <c:choose>
                    <c:when test="${sessionScope.role == 'aspirant'}">
                        <th><fmt:message key="content.vacancy.more"/></th>
                    </c:when>
                    <c:when test="${sessionScope.role == 'admin'}">
                        <th><fmt:message key="content.vacancy.more"/></th>
                    </c:when>
                </c:choose>
            </tr>

            <c:forEach items="${requestScope.vacancy_list}" var="vacancy">
                <tr>

                    <td>${vacancy.name}</td>
                    <td>${vacancy.uploadDate}</td>
                    <td>${vacancy.organizationName}</td>
                            <td>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#vacancy-${vacancy.id}-modal">
                                    <fmt:message key="content.add.request"/>
                                </button>
                                <c:choose>
                                <c:when test="${sessionScope.role == 'aspirant'}">
                                <!-- The Modal -->
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
                                            <form name="profileForm" method="POST" action="controller">
                                                <input class="form-control" type="hidden" name="vacancy_id"
                                                       value="${vacancy.id}"/>
                                                <!-- Modal body -->
                                                <div class="modal-body">
                                                        <%--TODO: add local content--%>
                                                    <h4>Requirements:</h4>
                                                            <p>${vacancy.requirement}</p>
                                                            <a href="${vacancy.organizationWebsite}">Go to their website</a>
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
                                                <form name="confirmForm" method="POST" action="controller">
                                                    <input class="form-control" type="hidden" name="command" value="confirm_vacancy"/>
                                                    <input class="form-control" type="hidden" name="vacancy_id" value="${vacancy.id}"/>

                                                    <!-- Modal body -->
                                                    <div class="modal-body">
                                                            <%--TODO: add local content--%>
                                                        <h4>Requirements:</h4>
                                                        <p>${vacancy.requirement}</p>
                                                        <a href="${vacancy.organizationWebsite}">Go to their website</a>
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

                                                        <input type="submit" class="btn btn-primary" value="ConfirmVac"/>
                                                        <input type="submit" class="btn btn-danger" value="delete_vacancy"/>
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal">
                                                                <%--TODO: add local content--%>Close
                                                        </button>
                                                    </div>
                                                </form>

                                            </div>
                                        </div>

                                    </c:when>
                                    </c:choose>
                                </div>
                            </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <ul class="pagination text-center">
            <li class="page-item">
                <button class="page-link disabled" disabled>Previous</button>
            </li>
            <li class="page-item">
                <button class="page-link">Next</button>
            </li>
        </ul>
        <c:if test="${sessionScope.user_org_info != null}">
            <c:import url="register_vacancy.jsp"/>
        </c:if>
    </div>
</div>
<footer class="container-fluid bg-dark text-white p-2">
    <div class="container">
        <ctg:role-time role="${sessionScope.role}"/>
    </div>
</footer>
</body>
</html>
