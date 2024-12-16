<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Crear Usuario</title>
</head>

<body>

<h1>Crear Usuario</h1>

<form action="CrearUsuario" method="POST">

<label for="username">Usuario: </label>
<input type="text" id="username" name="username" required><br>

<label for="password">Contraseña: </label>
<input type="password" id= "password" name="password" required><br>

<label for="email">Correo Electronico: </label>
<input type="email" id="email" name="email" required><br>
<button type= "submit">Crear</button>

</form>

<c:if test="${not empty mensaje}">
<p style="color: green;">${mensaje} </p>
</c:if>
</body>
</html>