<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person CRUD</title>
</head>
<body>
	
	<form action="persistPerson" method="get">
		<table border="1">
			<tr style="text-align: center;">
				<td colspan="2"><h3>Register Person</h3></td>
			</tr>
			<tr>
				<td>
					<label>Nome:</label>
				</td>
				<td>
					<input type="text" name="nome" value="${person.nome}">
				</td>				
			</tr>	
			<tr>
				<td>
					<label>CPF:</label>
				</td>
				<td>
					<input type="text" name="cpf" value="${person.cpf}">
				</td>			
			</tr>	
			<tr>
				<td><input type="submit" value="Salvar"/></td>
				<td><input type="reset" value="Cancelar"></td>
			</tr>		
		</table>	
	</form>
	<br>
	<hr>
	
	<h3>Person List</h3>
	<ul>
		<c:forEach var="person" items="${list}">
			<li>${person.id} - ${person.nome} - ${person.cpf}</li> 		
		</c:forEach>
	</ul>

</body>
</html>
