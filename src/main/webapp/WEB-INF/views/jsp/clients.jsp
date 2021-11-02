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
    <title>Clients</title>
</head>
<body>
<header>
    <div class="header_wrapper">
        <div class="row">
            <h1 class="col">Your CRM</h1>
            <button class='col add_task_button header__button' id="callback-button" onclick="openModal()">Add client
            </button>
            <a href="/tasks" class="col">Three days plan</a>
            <a href="/profile" class="col">Profile</a>
        </div>
    </div>
</header>
<main>
    <div class="wrapper">
        <div class = 'clients_blocks'>
            <c:forEach items="${clients}" var="client">
                <div class="client_block">
                    <a href="/clients/client?cid=${client.id}" class="client_link">
                        <p class="client_block--name">${client.firstName} ${client.lastName}</p>
                        <p class="client_block--number">${client.mobileNum}</p>
                        <p class="client_block--birth_date">${client.birthDate}</p>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
<div class="modal" id="modal">
    <div class="modal_content">
        <form action="/clients" method="post" id="add_task_form" onsubmit="return isAllValid()">
            <div class="add_task_name add_task_block">
                <input type="text" name="name" placeholder="Client name"
                       required id="name">
            </div>
            <div class="add_task_name add_task_block">
                <input type="text" name="surname" placeholder="Client surname"
                       required id="surname">
            </div>
            <div class="add_task_name add_task_block">
                <input type="text" name="email" placeholder="Client email" required
                id="email">
            </div>
            <div class="add_task_name add_task_block">
                <input type="text" name="mobileNum" placeholder="Client mobile number"
                       required id="mobileNum">
            </div>
            <div class="add_task_date add_task_block">
                <input type="date" name="birthDate" id="add_client_birthday" required>
            </div>
            <div class="add_task_description add_task_block">
                <textarea name="description" placeholder="Client info" maxlength="600" required></textarea>
            </div>
            <input type="submit" name="submit" value="Add!" class="edit">
        </form>
    </div>
</div>

<script type="text/javascript">

    function checkAlphabetic(elem){
        let alphaPattern = /[A-Za-z]+/;
        return elem.match(alphaPattern);
    }

    function isNumberValid(mobileNum){
        let numsPattern = /(\+7|7|8)?[\s\-]?\(?[489][0-9]{2}\)?[\s\-]?[0-9]{3}[\s\-]?[0-9]{2}[\s\-]?[0-9]{2}$/;
        return mobileNum.match(numsPattern)
    }
    function isEmailValid(email){
        let mailFormat = /[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return email.match(mailFormat);
    }
    function isAllValid(){
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let email = document.getElementById('email').value;
        let mobileNum = document.getElementById('mobileNum').value;

        if (isEmailValid(email) &&
            isNumberValid(mobileNum) &&
            checkAlphabetic(name) &&
            checkAlphabetic(surname)){
            return true;
        }else{
            alert("Something isn't valid, please check fields again");
            return false;
        }
    }



    var modal1 = document.getElementById('modal');
    var tagBody = document.getElementById('body');

    function openModal() {
        modal1.classList.add('modal_active');
        tagBody.classList.add('hidden');
    }

    modal1.onmousedown = function (e) {
        let target = e.target;
        let modalContent = modal1.getElementsByClassName('modal_content')[0];
        if (e.target.closest('.' + modalContent.className) === null) {
            this.classList.remove('modal_active');
            tagBody.classList.remove('hidden');
        }
    };

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
