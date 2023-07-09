<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions Manager</title>
</head>
<body>
<div align="center">
    <h2>Questions Manager</h2>
    <form method="get" action="search">
        <input type="text" name="keyword" />
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/new">New Question</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>question_id</th>
            <th>section_id</th>
            <th>answers_number</th>
            <th>correct_answer</th>
        </tr>
        <c:forEach items="${listQuestions}" var="question">
        <tr>
            <td>${question.question_id}</td>
            <td>${question.section_id}</td>
            <td>${question.answers_number}</td>
            <td>${question.correct_answer}</td>
            <td>
                <a href="/edit?id=${question.question_id}">Edit</a>

                <a href="/delete?id=${question.question_id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>