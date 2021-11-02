<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 29.09.2021
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Share Client!</title>
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
    <div class="wrapper">
        <div class='clients_blocks'>
            <c:forEach items="${users}" var="user">
                <div class="client_block">
                    <label for="${user.id}">
                        <p class="client_block--name">${user.name} ${user.surname}</p>
                        <p class="client_block--number">${user.email}</p>
                    </label>
                    <input type="checkbox" name="${user.id}" id="${user.id}" form="share_form">
                </div>
            </c:forEach>
        </div>
        <form id="share_form" method="post" action="/shareClient?cid=${clientId}" style="text-align: center">
            <input type="submit" value="Share!" class="big_submit_btn">
        </form>
    </div>
</main>
</body>
</html>
