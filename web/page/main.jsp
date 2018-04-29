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

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="content"/>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/style.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

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
                    
                    <c:import url="register.jsp"/>
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
<header class="container-fluid bg-dark text-white   p-2">


    <c:if test="${info != null}">
        <script>
            alert("${info}");
        </script>
    </c:if>


    <div class="container ">
        <div class="row">
            <div class="col-10">
                <nav>
                    <ul class="nav nav-pills ">
                        <li class="nav-item">
                            <a href="#" class="nav-link">Logo</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col">
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
                    <c:when test="${role!=null}">
                        <button class="btn btn-dark ">
                                <%--TODO: add local content--%>
                                ${role}
                        </button>
                        <!-- Button to Open the Modal -->
                        <button class="btn btn-primary ">
                                <%--TODO: add local content--%>
                            Log Out
                        </button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</header>
<div class="wrapper container">
    <ctg:role-time role="${role}"/>
    <section class="jumbotron">
        <h1>Bootstrap Tutorial</h1>
        <p>Bootstrap is the most popular HTML, CSS...</p>
    </section>
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

        <c:forEach items="${vacancy_list}" var="vacancy">
            <tr>
                <td>${vacancy.name}</td>
                <td>${vacancy.uploadDate}</td>
                <td>${vacancy.requirement}</td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#vacancy-${vacancy.id}-modal">
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
                                            <%--TODO: add local content--%>Close</button>
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
</body>
</html>
