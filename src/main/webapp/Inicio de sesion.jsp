<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Inicio de Sesion</title>
</head>

<body>
<h1>Inicio de Sesion</h1>
<form action = "IniciarSesion" method = "POST">
<label for = "username">Usuario: </label>
<input type = "text" id = "username" name = "username" required><br>
<label for= "password">Contraseña: </label>
<input type= "password" id= "password" name= "password" required><br>
<button type= "submit">Ingresar</button>
</form>

<c:if test= "${not empty error }">
<p style= "color: red;">${error}</p>
</c:if>

</body>
</html>