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
<body class="bg-secondary text-light">
	<h1>Welcome <c:out value="${user.userName}"></c:out></h1>
	<h3>One Book</h3>
	<div class="mt-3">
		<a href="/logout" class="text-light mt-5">Logout </a>
	</div>
	<div class="container bg-dark text-light mt-5 p-5">
		<table  class="table table-dark">
		    <thead>
		        <tr>
		            <th scope="col">ID</th>
		            <th scope="col">Title</th>
		            <th scope="col">Author</th>
		            <th scope="col">Posted By</th>
		            <th scope="col">Thoughts</th>
		        </tr>
		    </thead>
		    <tbody>   
		    		<tr>
		    			<td> <c:out value="${book.id}"></c:out></td>
		    			<td> <c:out value="${book.title}"></c:out></td>
		    			<td> <c:out value="${book.author}"></c:out></td>
		    			<td> <c:out value="${book.user.userName}"></c:out></td>
		    			<td> <c:out value="${book.thoughts}"></c:out></td>
    		</tbody>
    	</table>
    <div class="mt-3">
		<a href="/newBook" class="text-light" >+ Add a book to my shelf! </a>
	</div>
    </div>
</body>
</html>