<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>

    <%--<form>--%>
        <%--Rollnumber:<input type="number" value="${rollno}" name="rollno"><br>--%>
        <%--Enter the semester:<input type="number" name="semester" min="0" max="6">--%>
        <%--<input type="submit" value="okay">--%>
    <%--</form>--%>

    <%--<%--%>
        <%--String userName = request.getParameter("userName");--%>
        <%--if(userName!=null &&--%>
                <%--!"".equals(userName)){--%>
        <%--session.setAttribute("userName",userName);--%>
    <%--}--%>
    <%--%>--%>
    <%--<input type="text" id="userName" name="userName"/>--%>

    <%--<input type="button" onclick="<% request.setAttribute("userName", "user"); %>" value="user">--%>

    <form action="login.jsp">
    User: <select name="userName">
        <option selected disabled>(please select:)</option>
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select> <input type="submit" value="login">
    </form>

    Current user: ${userName}
    <hr>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>