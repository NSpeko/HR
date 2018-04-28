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
<header class="container-fluid bg-dark text-white   p-2">


    <c:if test="${info} != null">
        <fmt:message key="${info}"/>
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
                <button id="log-in-button" class="btn btn-dark " data-toggle="modal" data-target="#log-in-modal">
                    Log in
                </button>
                <div class="modal fade text-dark" id="log-in-modal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Sign Up</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <form name="loginForm" method="POST" action="controller">
                                <div class="modal-body text-center">
                                    <c:import url="log-in.jsp"/>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input class="btn btn-primary float-left" type="submit" value="Submit"/>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <!-- Button to Open the Modal -->
                <button id="sign-up-button" class="btn btn-primary " data-toggle="modal" data-target="#sign-up-modal">
                    Sign Up
                </button>

                <!-- The Modal -->
                <div class="modal fade text-dark" id="sign-up-modal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Sign Up</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <!-- Modal body -->
                            <form name="loginForm" method="POST" action="controller">
                                <div class="modal-body text-center">
                                    <c:import url="register.jsp"/>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input class="btn btn-primary float-left" type="submit" value="Submit"/>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
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
            <th>Name</th>
            <th>Date</th>
            <th>Requirement</th>
            <th>More</th>
        </tr>

        <c:forEach items="${vacancy_list}" var="vacancy">
            <tr>
                <td>${vacancy.name}</td>
                <td>${vacancy.uploadDate}</td>
                <td>${vacancy.requirement}</td>
                <td>
                    <button class="btn btn-primary">
                        Response
                    </button>
                </td>
            </tr>
        </c:forEach>

    </table>

    <ul class="pagination text-center">
        <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
    <br/>
    ${error}
    <br/>
</body>
</html>
