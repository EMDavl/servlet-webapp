<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 29.09.2021
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Clients</title>
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
        <div class = 'clients_blocks'>
            <div class="client_block">
                <a href="#" class="client_link">
                    <p class="client_block--name">Name</p>
                    <p class="client_block--number">Number</p>
                    <p class="client_block--birth_date">Birth date</p>
                </a>
            </div>
        </div>
    </div>
</main>
<script type="text/javascript">
    var clientBlocks = document.getElementsByClassName('client_block');
    for (let i = 0; i < clientBlocks.length; i++) {
        clientBlocks[i].addEventListener('click', () => {

        })
    }
</script>
</body>
</html>
