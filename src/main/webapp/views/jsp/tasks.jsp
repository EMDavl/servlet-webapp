<%--
  Created by IntelliJ IDEA.
  User: PC-1
  Date: 29.09.2021
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

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
            <c:forEach items="${tasks}" var="task">
                <div class="task_block">
                    <span class="task_header">${task.taskName}</span>

                    <p class="task_description">${task.description}</p>

                    <div class="task_members">Members: ${task.membersCount}</div>
                    <div class="task_date">Date and time: ${task.getFormattedDateTime()}</div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<div class="modal" id="modal">
    <div class="modal_content">
        <form action="/add-task" method="post" id="add_task_form">
            <div class="add_task_name add_task_block">
                <input type="text" name="taskName" placeholder="Task name" required>
            </div>
            <div class="add_task_date add_task_block">
                <input type="date" name="taskDate" id='add_task_date' required>
                <input type="time" name="taskTime" id='add_task_time' max="23:59" required>

            </div>
            <div class="add_task_description add_task_block">
                <textarea name="taskDescription" placeholder="Task description" maxlength="600" required></textarea>
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

    function getTomorrow() {
        var tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        var dd = tomorrow.getDate();
        var mm = tomorrow.getMonth() + 1;
        var yyyy = tomorrow.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        tomorrow = yyyy + '-' + mm + '-' + dd;
        return tomorrow;
    }

    document.getElementById("add_task_date").setAttribute("min", getTomorrow());
</script>
</body>
</html>