<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body class="bg-secondary">
  <div class="container mt-5 p-5 bg-dark text-light">
	<h1>Edit</h1>
	<div class="mt-3">
		<a href="/logout" class="text-light mt-5">Logout </a>
	</div>
 	 <form:form action="/editBook/update/${id}" method="post" modelAttribute="editBook">
 	 <input type="hidden" name="_method" value="put"/>
 	 <form:hidden path="user" value="${editBook.user.id }"/>
        <div class="form-group">
            <label>Title:</label>
            <form:input path="title" class="form-control" />
             <form:errors path="title" class="text-danger" />
        </div>
        <div class="form-group">
            <label>Author:</label>
            <form:input path="author" class="form-control" />
            <form:errors path="author" class="text-danger" />
         </div>
         <div class="form-group">
            <label>My thoughts:</label>
            <form:input path="thoughts" class="form-control" />
             <form:errors path="thoughts" class="text-danger" />
         </div>
        <input type="submit" value="Edit Book" class="btn btn-primary" />
    </form:form>
    <div class="mt-5 ">
		<a href="/dashboard" class="text-light" >Back to shelves</a>
	</div>
    </div>
</body>
</html>