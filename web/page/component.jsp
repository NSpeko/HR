<%--
  Created by IntelliJ IDEA.
  User: hoi
  Date: 5/4/2018
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="authentication_content"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<section class="notifications" >
    <section class="content">
        <div class="notificationsSlides">
            <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex
                ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                mollit
                anim id est laborum.
            </p>
        </div>

        <div class="notificationsSlides">
            <p> Definetly not Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex
                ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                mollit
                anim id est laborum.
            </p>
        </div>

        <div class="notificationsSlides">
            <img src="https://i.redditmedia.com/gq0wN9LQA1fKw8ob7eFvytQMQUeT0UCuqazAjBA1IYo.jpg?w=583&s=70d813c27df45b99c7fe5d07de0d61c5">
        </div>

    </section>
    <button class="close-sign" onclick="document.getElementById('notifications-wrapper').remove();">&#120</button>
    <section class="content controls">
        <div class="tips-disabler">
            <input type="checkbox" id="disable-tips"
                   onclick="localStorage.setItem('tipsAreDisabled',this.checked?'true':'false');">
            <label for="disable-tips">Disable Tips</label>
        </div>
        <div class="slider-wrapper">
            <button onclick="plusDivs(-1)">&#10094;</button>
            <div id="slider-selector">
            </div>
            <button onclick="plusDivs(+1)">&#10095;</button>
        </div>
    </section>
</section>
<script>
    showDots();
    showDivs(slideIndex);
</script>
</body>
</html>
