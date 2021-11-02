<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 09.10.2021
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
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
            <div>
                <input type="text"
                       name="email"
                       form="edit_profile"
                       value="${email}"
                       class="change_form_field"
                       id="email">

                <button
                        class="blue_btn button block_btn"
                        id="add_pass_btn"
                >Edit password</button>

                <button
                        class="blue_btn button secret block_btn"
                        id="remove_pass_btn"
                >Don't edit password</button>

                <input
                        type="password"
                        name="oldPass"
                        form="edit_profile"
                        class="change_form_field secret"
                        placeholder="Old password"
                        id="old_pass"
                        minlength=8>

                <input
                        type="password"
                        name="newPass"
                        form="edit_profile"
                        class="change_form_field secret"
                        placeholder="New password"
                        id="new_pass"
                        minlength=8>
            </div>
            <div class="profile_buttons">
                <form action="/edit-profile" method="post" id='edit_profile' name="edit_profile" onsubmit="return isEmailValid()">
                    <input type="submit" name="submit" value="Save" class="button blue_btn">
                </form>
                <button class="button red_btn" id='cancel_btn'>Cancel</button>
            </div>
        </div>
    </div>
</main>
<script type="text/javascript">
    document.getElementById('cancel_btn').onclick = function (){
        location.href = '/profile';
    };
    let addPassBtn = document.getElementById('add_pass_btn');
    let removePassBtn = document.getElementById('remove_pass_btn');
    let oldPass = document.getElementById('old_pass');
    let newPass = document.getElementById('new_pass');


    addPassBtn.onclick = function(){
        addPassBtn.classList.add('secret');
        removePassBtn.classList.remove('secret');
        oldPass.classList.remove('secret');
        newPass.classList.remove('secret');
    }

    removePassBtn.onclick = function(){
        addPassBtn.classList.remove('secret');
        removePassBtn.classList.add('secret');
        newPass.value = '';
        oldPass.value = '';
        oldPass.classList.add('secret');
        newPass.classList.add('secret');
    }

    function isEmailValid(){
        let mailFormat = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        let email = document.getElementById('email').value;
        if(email.match(mailFormat) || email === ''){
            return true;
        }else{
            alert('Email isn\'t valid!')
            return false;
        }
    }

    <c:if test="${emailErr}">
        alert("Email isn't unique, try another one")
    </c:if>
    <c:if test="${passwordErr}">
        alert("Old password doesn't matches")
    </c:if>
</script>
</body>
</html>

