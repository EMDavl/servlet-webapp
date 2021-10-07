<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 29.09.2021
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta lang="en">
    <link rel="stylesheet" type="text/css" href="<c:url value="/views/css/styles.css"/>">
    <title>Tasks</title>
</head>
<body>
<header>
    <div class="header_wrapper">
        <div class="row">
            <h1 class="col">Your CRM</h1>
            <a href="/clients" class="col">Clients</a>
            <a href="/tasks" class="col">Three days plan</a>
            <button class='col add_task_button header__button' id="callback-button" onclick="openModal()">Add task
            </button>
            <a href="/profile" class="col">Profile</a>
        </div>
    </div>
</header>
<main>
    <div class="wrapper">
        <div class="tasks">
            <div class="task_block">
                <span class="task_header">Drink coffee</span>

                <p class="task_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>

                <span class="task_date">25.09.2021 14:00</span>
            </div>
            <div class="task_block">
                <span class="task_header">Drink coffee</span>

                <p class="task_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>

                <span class="task_date">25.09.2021 14:00</span>
            </div>
            <div class="task_block">
                <span class="task_header">Drink coffee</span>

                <p class="task_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>

                <span class="task_date">25.09.2021 14:00</span>
            </div>
            <div class="task_block">
                <span class="task_header">Drink coffee</span>

                <p class="task_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>

                <span class="task_date">25.09.2021 14:00</span>
            </div>
        </div>
    </div>
</main>

<div class="modal" id="modal">
    <div class="modal_content">
        <form action="/add-task" method="post" id="add_task_form">
            <div class="add_task_name add_task_block">
                <input type="text" name="task_name" placeholder="Task name">
            </div>
            <div class="add_task_date add_task_block">
                <input type="date" name="task_date" id='add_task_date'>
            </div>
            <div class="add_task_description add_task_block">
                <textarea name="task_description" placeholder="Task description" maxlength="600"></textarea>
            </div>
            <input type="submit" name="submit" value="Add!" class="edit">
        </form>
    </div>
</div>
<script type="text/javascript">
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

    function getToday() {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1;
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + '-' + mm + '-' + dd;
        return today;
    }

    document.getElementById("add_task_date").setAttribute("min", getToday());
</script>
</body>
</html>