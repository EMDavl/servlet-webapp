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

<html>
<head>
    <meta charset="UTF-8">
    <meta lang="en">
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
                <p>${user.getFullName()}</p>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">email</p>
                    <p class="profile_content_info-elem-value">${user.getEmail()}</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">clients now</p>
                    <p class="profile_content_info-elem-value">${user.getClientsNow()}</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">tasks now</p>
                    <p class="profile_content_info-elem-value">${user.getTasksNow()}</p>
                </div>
            </div>
            <a class="edit">edit</a>
        </div>
    </div>
</main>
</body>
</html>
