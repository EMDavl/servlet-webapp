<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 06.10.2021
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta lang="en">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Sign in</title>
</head>
<body>
<header>
    <div class="header_wrapper">
        <div class="row">
            <h1 class="col">Your CRM</h1>
            <a href="/welcome" class="col">About us</a>
            <a href="/sign-in" class="col">Sign in</a>
        </div>
    </div>
</header>
<main class="sign_main">
    <div class="wrapper">
        <div class="sign_in_form">
            <form action="/sign-in" method="post">
                <div class="sign_in_form--block">
                    <label for="email">Введите email: </label>
                    <input type="text" name="email" placeholder ="email" id="email">
                </div>
                <div class="sign_in_form--block">
                    <label for="password">Введите пароль: </label>
                    <input type="password" name="password" placeholder ="password" id="password">
                </div>
                <div class="sign_in_form--btn">
                    <input type="submit" name="subm" value="sign in">
                </div>
            </form>
        </div>
        <p>Haven't account yet?</p>
        <a href="/sign-up">Sign up!</a>
    </div>
</main>
<c:if test="${error}">
    <script type="text/javascript">alert("Wrong email or password")</script>
</c:if>
</body>
</html>
