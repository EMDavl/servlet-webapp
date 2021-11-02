<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 29.09.2021
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="ru.itis.models.UserModel" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Profile</title>
</head>
<body>
<header>
    <div class="header_wrapper">
        <div class="row">
            <h1 class="col">Your CRM</h1>
            <a href="/clients" class="col">Clients</a>
            <a href="/tasks" class="col">Three days plan</a>
            <a href="/profile" class="col">Profile</a>
        </div>
    </div>
</header>
<main>
    <div class="profile_wrapper">
        <div class="profile_content">
            <div id="profile_content_info">
                <p>${user.fullName}</p>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">email</p>
                    <p class="profile_content_info-elem-value">${user.email}</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">clients now</p>
                    <p class="profile_content_info-elem-value">${user.clientsNow}</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">tasks now</p>
                    <p class="profile_content_info-elem-value">${user.tasksNow}</p>
                </div>
            </div>
            <div class="profile_buttons">
                <button class="button blue_btn" id='edit_btn'>Edit profile</button>
                <form action="/logout" method="post">
                    <input type="submit" name="submit" value="Logout" class="button red_btn">
                </form>
            </div>
        </div>
    </div>
</main>
<script type="text/javascript">
    document.getElementById('edit_btn').onclick = function (){
        location.href = '/edit-profile';
    };
</script>
</body>
</html>