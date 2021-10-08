<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 06.10.2021
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>${client.name}</title>
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
                <p>Emil Davlyatov</p>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">email</p>
                    <p class="profile_content_info-elem-value">em.davl@ya.ru</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">birth date</p>
                    <p class="profile_content_info-elem-value">15-09-2002</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">mobile number</p>
                    <p class="profile_content_info-elem-value">88005553535</p>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">info</p>
                    <p class="profile_content_info-elem-value">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>
            </div>
            <button class="edit_button client_button edit">Edit</button>

            <form action="/delete-user/${id}" method="post">
                <input type="submit" name="delete">
            </form>
        </div>
    </div>
</main>

<script type="text/javascript">
    //TODO Сделать измненение клиента и всплывашку для подтверждения удаления
</script>
</body>
</html>