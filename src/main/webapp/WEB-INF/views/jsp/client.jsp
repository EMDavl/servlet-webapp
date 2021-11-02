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
    <title>${client.firstName} ${client.lastName}</title>
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
                <p class="shown">${client.firstName} ${client.lastName}</p>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title secret">First name</p>
                    <input
                            type="text"
                            form="client_edit_form"
                            name="clientFirstName"
                            class="secret profile_content_info-elem-value"
                            value="${client.firstName}"
                            id='name'>
                    <p class="profile_content_info-elem-title secret">Last name</p>

                    <input
                            type="text"
                            form="client_edit_form"
                            name="clientLastName"
                            class="secret profile_content_info-elem-value"
                            value="${client.lastName}"
                            id="surname">
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">email</p>
                    <p class="profile_content_info-elem-value shown">${client.email}</p>
                    <input
                            type="text"
                            form="client_edit_form"
                            name="clientEmail"
                            value="${client.email}"
                            class="secret profile_content_info-elem-value"
                            id="email">
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">birth date</p>
                    <p class="profile_content_info-elem-value shown">${client.birthDate}</p>

                    <input
                            type="date"
                            form="client_edit_form"
                            name="birthDate"
                            value="${client.birthDate}"
                            class="secret profile_content_info-elem-value"
                            id='add_client_birthday'>
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">mobile number</p>
                    <p class="profile_content_info-elem-value shown">${client.mobileNum}</p>
                    <input
                            type="tel"
                            name="mobileNum"
                            value="${client.mobileNum}"
                            form="client_edit_form"
                            class="secret profile_content_info-elem-value"
                            id="mobileNum">
                </div>
                <div class="profile_content_info-elem">
                    <p class="profile_content_info-elem-title">info</p>
                    <p class="profile_content_info-elem-value shown">${client.description}</p>
                    <div class="add_task_description">
                        <textarea
                                name="description"
                                form="client_edit_form"
                                maxlength="600"
                                class="add_task_description secret profile_content_info-elem-value">${client.description}</textarea>
                    </div>
                </div>
            </div>
            <div>
                <button id="edit_btn" class="button blue_btn shown">Edit</button>
                <form id="client_edit_form" name="client_edit_form" action="/clients/client?cid=${client.id}" method="post"
                      onsubmit="return isAllValid()">
                    <input type="submit" class="blue_btn button secret"
                            value="Save">
                </form>
                <button class="button red_btn secret" id="cancel_btn">Cancel</button>
                <form action="/clients/delete-client?cid=${client.id}" method="post">
                    <input type="submit" name="delete" value="Delete client" class="button red_btn shown">
                </form>
                <button class="button blue_btn shown" id="share_btn">Share client</button>
            </div>
        </div>
    </div>
</main>

<script type="text/javascript">
    document.getElementById('share_btn').onclick = function (){
        location.href='/shareClient?cid=${client.id}';
    }

    document.getElementById('edit_btn').onclick = function () {
        var showedElements = document.getElementsByClassName('shown');
        var hiddenElements = document.getElementsByClassName('secret');
        for (let i = 0; i < hiddenElements.length; i++) {
            hiddenElements[i].style.display = 'block';
        }
        for (let i = 0; i < showedElements.length; i++) {
            showedElements[i].style.display = 'none';
        }
    }

    function checkAlphabetic(elem){
        let alphaPattern = /[A-Za-z]+/;
        return elem.match(alphaPattern);
    }

    function isNumberValid(mobileNum){
        let numsPattern = /^(\+7|7|8)?[\s\-]?\(?[489][0-9]{2}\)?[\s\-]?[0-9]{3}[\s\-]?[0-9]{2}[\s\-]?[0-9]{2}$/;
        return mobileNum.match(numsPattern)
    }
    function isEmailValid(email){
        let mailFormat = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return email.match(mailFormat);
    }
    function isAllValid(){
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let email = document.getElementById('email').value;
        let mobileNum = document.getElementById('mobileNum').value;

        alert("i'm here")

        if (isEmailValid(email) &&
            isNumberValid(mobileNum) &&
            checkAlphabetic(name) &&
            checkAlphabetic(surname)){
            return true;
        }else{
            alert("Something isn't valid, please check fields again");
            return false
        }
    }

    document.getElementById('cancel_btn').onclick = function () {
        var showedElements = document.getElementsByClassName('shown');
        var hiddenElements = document.getElementsByClassName('secret');
        for (let i = 0; i < showedElements.length; i++) {
            showedElements[i].style.display = 'block';
        }
        for (let i = 0; i < hiddenElements.length; i++) {
            hiddenElements[i].style.display = 'none';
        }
    }

    function getMaxYear() {
        var date = new Date();
        var dd = date.getDate();
        var mm = date.getMonth() + 1;
        var yyyy = date.getFullYear() - 18;
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        date = yyyy + '-' + mm + '-' + dd;
        return date;
    }

    document.getElementById("add_client_birthday").setAttribute("max", getMaxYear());
    document.getElementById("add_client_birthday").setAttribute("value", getMaxYear());


</script>
</body>
</html>