<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Modificar Usuario</title>
</head>

<body>
<h1>Modificar Usuario</h1>

<form action = "ModificarUsuario" method="POST">
<input type = "hidden" name = "id" value = "$ {usuario.id}">

<label for = "username">Nombre de Usuario:</label>
<input type = "text" id = "username" name = "username" value = "$ {usuario.username}" required><br>

<label for = "email">Correo Electronico:</label>
<input type = "email" id = "email" name = "email" value = "$ {usuario.email}" required><br>

<button type = "submit">Guardar Cambios</button>

</form>

<c:if test = "$ {not empty error">
<p style = "color: red;"> $ {error} </p>
</c:if>


</body>
</html>