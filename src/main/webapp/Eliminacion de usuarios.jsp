<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Eliminacion de Usuario</title>
</head>

<body>

<h1>Eliminar Usuario</h1>

<form action="EliminarUsuario" method="POST">

<label for="userId">ID del Usuario: </label>
<input type="number" id= "userId" name="userId" required><br>
<button type="submit">Eliminar</button>

</form>

<c:if test="${not empty mensaje}">
<p style="color: green;">${mensaje}</p>
</c:if>

<c:if test="${not empty error}">
<p style="color: red;">${error}</p>
</c:if>

</body>
</html>