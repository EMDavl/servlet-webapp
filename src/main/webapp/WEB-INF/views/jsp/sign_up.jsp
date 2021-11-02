<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 01.10.2021
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<html>
<head>
    <meta charset="utf-8">
    <meta lang="en">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Sign up</title>
</head>
<body>
<header>
    <div class="header_wrapper">
        <div class="row">
            <h1 class="col">Your CRM</h1>
            <a href="/" class="col">About us</a>
            <a href="/sign-in" class="col">Sign in</a>
        </div>
    </div>
</header>
<main class="sign_main">
    <div class="wrapper">
        <div class="sign_in_form">
            <form action="/sign-up" method="post" onsubmit="return validateForm()">
                <div class="sign_in_form--block">
                    <label for="email">Введите email: </label>
                    <input type="text" name="email" placeholder ="email" id="email" onblur="validateEmail()" required>
                    <div id="emailMessage" class="message"></div>
                </div>

                <div class="sign_in_form--block">
                    <label for="name">Введите имя: </label>
                    <input type="text" name="name" placeholder ="name" id="name" required>
                </div>

                <div class="sign_in_form--block">
                    <label for="surname">Введите фамилию: </label>
                    <input type="text" name="surname" placeholder ="surname" id="surname" required>
                </div>

                <div class="sign_in_form--block">
                    <label for="password">Введите пароль: </label>
                    <input type="password"
                           name="password"
                           placeholder ="password"
                           id="password"
                           onkeyup='checkPasswordConfirmation();'
                           minlength = 8
                           required>
                </div>

                <div class="sign_in_form--block">
                    <label>Подтвердите пароль: </label>
                    <input type="password" name="confirm_password" placeholder ="confirm password"
                           id="confirm_password"
                           onkeyup='checkPasswordConfirmation();'
                           required>
                    <div id="passMessage" class="message"></div>
                </div>

                <div class="sign_in_form--btn">
                    <input type="submit" name="subm" value="sign up" id="submit">
                </div>
            </form>

            <c:if test="${error}">
                <script type="text/javascript">alert("Email is already taken")</script>
            </c:if>
        </div>
    </div>
</main>

<script type="text/javascript">

    function checkPasswordConfirmation() {
        let mes = document.getElementById('passMessage');

        if (isPasswordsSame()) {
            mes.innerHTML = '';
        } else {
            mes.innerHTML = 'not matching';
        }
    }

    function validateEmail()
    {
        let mes = document.getElementById('emailMessage');
        if(!isEmailValid())
        {
            mes.innerHTML = 'email doesn\'t valid';
        }
    }

    function isPasswordsSame() {
        let pass = document.getElementById('password');
        let c_pass = document.getElementById('confirm_password');
        return pass.value ===
            c_pass.value;
    }

    function isEmailValid() {
        let mailformat = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        let email = document.getElementById('email');
        return email.value.match(mailformat);
    }

    function validateForm() {

        if(!isPasswordsSame()){
            alert('Password don\'t match with its confirmation field');
            return false;
        }

        if(!isEmailValid()){
            alert('Email isn\'t valid');
            return false;
        }

        return true;
    }
</script>
</body>
</html>
