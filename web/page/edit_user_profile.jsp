<%--
  Created by IntelliJ IDEA.
  User: mtx.by
  Date: 28.05.2018
  Time: 16:47
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
<div class="modal fade text-dark" id="user-edit-profile-modal">
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
            <form name="editForm" method="POST" action="controller">
                <div class="modal-body ">
                    <div id="sign-up-as-user-form">
                        <input class="form-control" type="hidden" name="command" value="edit_user"/>
                        <br/><fmt:message key="content.user.first.name"/><br/>
                        <input class="form-control" value="${sessionScope.user_info.firstName}" type="text" name="first_name" title="<fmt:message key="content.input.title.name"/>" placeholder="name*" pattern="[A-ZА-Я][a-zа-я]{1,44}"/>
                        <br/><fmt:message key="content.user.last.name"/><br/>
                        <input class="form-control" type="text" value="${sessionScope.user_info.lastName}" name="last_name" placeholder="surname*" title="<fmt:message key="content.input.title.name"/>"
                               pattern="[A-ZА-Я][a-zа-я]{1,44}"/>
                        <br/><fmt:message key="content.user.email"/><br/>
                        <input class="form-control" type="email" name="email" value="${sessionScope.user_info.email}" placeholder="default@example.com" title="<fmt:message key="content.input.title.email"/>"
                               pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*[@]([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])"
                               required/>
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
</body>
</html>
