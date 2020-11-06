<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
</head>
<body>

	<a href="/helpstury">Home</a>
	<hr>
	
	<form action="userPersist">
		<table border="1">
			<tr style="text-align: center; text-decoration: underline;">
				<td colspan="2"><h3>User Register</h3></td>
			</tr>
			<tr>
				<td style="text-align: right;"><label>Login:</label></td>
				<td style="text-align: left: ;"> <input type="text" name="login"> </td>
			</tr>
			<tr>
				<td style="text-align: right;"><label>Password:</label></td>
				<td style="text-align: left: ;"> <input type="password" name="password"> </td>
			</tr>
			<tr>
				<td> &nbsp; </td>
				<td> 
					<input type="submit" value="Register">
					<input type="reset" value="Cancelar"> 
				</td>
			</tr>

		</table>
	</form>
	<br>
	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<th colspan="3"><h3>List User</h3></th>
			</tr>
			<tr>
				<th>ID</th><th>Login</th><th>Password</th>
			</tr>
		</thead>	
		<tbody>		
			<c:forEach var="user" items="${list}">
				<tr>
					<td>${ user.id }</td>
					<td>${ user.login }</td>
					<td>${ user.password }</td>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>