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
    <title>Human Resources</title>
</head>
<body>
<div class="modal fade text-dark" id="log-in-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <%--TODO: add local content--%>
                    Sign Up
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
                    <input class="btn btn-primary float-left" type="submit" value="Submit"
                    <%--TODO: add local content--%>/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <%--TODO: add local content--%>Close
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- The Modal -->
<div class="modal fade text-dark" id="sign-up-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">
                    <%--TODO: add local content--%>
                    Sign Up
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
                    <input class="btn btn-primary float-left" type="submit" value="Submit"
                    <%--TODO: add local content--%>/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <%--TODO: add local content--%>Close
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
                    <%--TODO: add local content--%>
                    Profile
                </h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form name="profileForm" method="POST" action="controller">
                <div class="modal-body ">
                    <p>first_name - ${sessionScope.user_info.firstName}</p>
                    <p>last_name - ${sessionScope.user_info.lastName}</p>
                    <p>email - ${sessionScope.user_info.email}</p>
                    <p>org_name - ${sessionScope.user_org_info.name}</p>
                    <p>website - ${sessionScope.user_org_info.website}</p>
                    <c:if test="${sessionScope.user_org_info == null}">
                        <button class="btn btn-primary " data-toggle="collapse" data-target="#org-register-form">Add
                            Organisation
                        </button>
                    </c:if>

                    <div id="org-register-form" class="collapse">
                        <div id="sign-up-org-form"><c:import url="register_org.jsp"/></div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <input class="btn btn-primary float-left" type="submit" value="Submit"
                    <%--TODO: add local content--%>/>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                        <%--TODO: add local content--%>Close
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
                <div class="col-9">
                    <nav>
                        <ul class="nav nav-pills ">
                            <li class="nav-item">
                                <a href="#" class="nav-link">Logo</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col clearfix">
                    <div class="float-right">
                        <c:choose>
                            <c:when test="${role==null}">
                                <button id="log-in-button" class="btn btn-dark " data-toggle="modal"
                                        data-target="#log-in-modal">
                                        <%--TODO: add local content--%>
                                    Log in
                                </button>
                                <!-- Button to Open the Modal -->
                                <button id="sign-up-button" class="btn btn-primary " data-toggle="modal"
                                        data-target="#sign-up-modal">
                                        <%--TODO: add local content--%>
                                    Sign Up
                                </button>
                            </c:when>
                            <c:when test="${role!= null}">
                                <button class="btn btn-dark " data-toggle="modal"
                                        data-target="#user-profile-modal">
                                        <%--TODO: add local content--%>
                                        ${role}
                                </button>
                                <!-- Button to Open the Modal -->
                                <a href="${pageContext.request.contextPath}/controller?command=log_out">
                                    <button class="btn btn-primary " name="command" value="log_out">
                                            <%--TODO: add local content--%>
                                        Log Out
                                    </button>
                                </a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="wrapper container">
        <section class="jumbotron">
            <h1>Bootstrap Tutorial</h1>
            <p>Bootstrap is the most popular HTML, CSS...</p>
        </section>
        <div class="container-fluid form-group">
            <form class="row" name="filter" method="POST" action="controller">
                <input class="form-control" type="hidden" name="command" value="vacancy_filter"/>
                <div class="col-3">
                    <label for="firstSelect">Select 1</label>
                    <select name="sort_col" id="firstSelect" class="form-control ">
                        <option value="sort_by_empty_column"></option>
                        <option value="sort_by_name">Name</option>
                        <option value="sort_by_date">Date</option>
                        <option value="sort_by_organization">Organization</option>
                    </select>
                </div>
                <div class="col-3">
                    <label for="secondSelect">Select 2</label>
                    <select name="sort_type" id="secondSelect" class="form-control ">
                        <option value="empty"></option>
                        <option value="decrease">Decrease</option>
                        <option value="increase">Increase</option>
                    </select>
                </div>

                <div class="col-5">
                    <label for="searchInput">Search</label>
                    <input class="form-control " id="searchInput" type="text" name="search_field" placeholder="Search"/>
                </div>
                <div class="col-1">
                    <label for="searchButton">&#160;</label>
                    <input id="searchButton" class="btn btn-primary float-left" type="submit" value="Search"
                    <%--TODO: add local content--%>/>
                </div>
            </form>
        </div>
        <table class="table table-hover">

            <tr>
                <th>Name
                    <%--TODO: add local content--%></th>
                <th>Date
                    <%--TODO: add local content--%></th>
                <th>Requirement
                    <%--TODO: add local content--%></th>
                <th>More
                    <%--TODO: add local content--%></th>
            </tr>

            <c:forEach items="${requestScope.vacancy_list}" var="vacancy">
                <tr>
                    <td>${vacancy.name}</td>
                    <td>${vacancy.uploadDate}</td>
                    <td>${vacancy.requirement}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#vacancy-${vacancy.id}-modal">
                                <%--TODO: add local content--%>
                            Response
                        </button>

                        <!-- The Modal -->
                        <div class="modal fade" id="vacancy-${vacancy.id}-modal">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">
                                                <%--TODO: add local content--%>Modal Heading</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                            <%--TODO: add local content--%>
                                        Modal body..
                                        <c:import url="log-in.jsp"/>
                                            <%--TODO: change form source--%>
                                            ${vacancy.requirement}
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                <%--TODO: add local content--%>Close
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </td>
                </tr>
            </c:forEach>

        </table>
        <ul class="pagination text-center">
            <li class="page-item">
                <button class="page-link disabled" disabled>Previous</button>
            </li>
            <li class="page-item">
                <button class="page-link">Next</button>
            </li>
        </ul>
        <br/>
        ${error}
        <br/>
    </div>
</div>
<footer class="container-fluid bg-dark text-white p-2">
    <div class="container">
        <ctg:role-time role="${role}"/>
    </div>
</footer>
</body>
</html>
