<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.meiir.kz/functions" %>

<html>
<head>
    <title>Meals list</title>
    <style>
        .normal {
            color: green;
        }
        .excess {
            color: orangered;
        }
    </style>
</head>
<section>
    <h3><a href="index.html">Home</a> </h3>
    <hr/>
    <h2>Meals LIST</h2>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <a href="meals?action=sortByDate">Sort By Date</a>
    <br><br>
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
            <jsp:useBean id="meal" scope="page" type="kz.meiir.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>${fn:formatDateTime(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a> </td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>