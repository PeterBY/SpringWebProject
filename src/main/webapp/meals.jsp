<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>
<h2>Meal list</h2>

<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
    <TR>
        <TH>Date</TH>
        <TH>Description</TH>
        <TH>Calories</TH>
    </TR>
    <jsp:useBean id="mealWithExceededList" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${mealWithExceededList}">
        <TR <c:if test="${meal.exceed}"> style="color: red;" </c:if> >
            <TD><javatime:format value="${meal.dateTime}" style="MS"/></TD>
            <TD>${meal.getDescription()}</TD>
            <TD>${meal.calories}</TD>
        </TR>
    </c:forEach>
</TABLE>

<form method="post">
    <input title="DATE" type="datetime-local" name="date" value="${now}">
    <input title="DESCRIPTION"type="text" name="description"  value="description">
    <input title="CALORIES" type="number" name="calories" value="0">
    <button type="submit">ADD</button>
</form>

</body>
</html>
