<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Wordings Manager</title>
</head>
<body>
<div align="center">
    <h2>Wordings Manager</h2>
    <form method="get" action="search">
        <input type="text" name="keyword" />
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/new">New Wording</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>question_id айди вопроса</th>
            <th>question_text</th>
            <th>answer0</th>
            <th>answer1</th>
            <th>answer2</th>
            <th>answer3</th>
            <th>number_answers</th>
            <th>correct_answer</th>
        </tr>
        <c:forEach items="${listWordings}" var="wording">
        <tr>
            <td>${wording.question_id}</td>
            <td>${wording.question_text}</td>
            <td>${wording.answer0}</td>
            <td>${wording.answer1}</td>
            <td>${wording.answer2}</td>
            <td>${wording.answer3}</td>
            <td>${wording.questions.answers_number}</td>
            <td>${wording.questions.correct_answer}</td>
            <td>
                <a href="/edit?id=${wording.question_id}">Edit</a>

                <a href="/delete?id=${wording.question_id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>