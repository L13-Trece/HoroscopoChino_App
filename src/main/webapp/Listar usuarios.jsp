<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Lista de Usuarios</title>

</head>

<body>

<h1>Lista de Usuarios</h1>

<table border="1">

<thead>
<tr>
<th>ID</th>
<th>Usuario</th>
<th>Correo Electronico</th>
<th>Acciones</th>
</tr>
</thead>

<tbody>
<c:forEach var="usuario" items="${usuarios}">
<tr>
<td>${usuario.id}</td>
<td>${usuario.username}</td>
<td>${usuario.email}</td>
<td>
<a href="ModificarUsuario?id=${usuario.id}">Modificar</a>
<form action="EliminarUsuario" method="post" style="display:inline;">
<input type="hidden" name="userId" value="${usuario.id }"/>
<button type="submit">Eliminar</button>
</form>
</td>
</tr>
</c:forEach>
</tbody>
</table>

<c:if test="${not empty mensaje}">
<p style= "color: green;">${mensaje}</p>
</c:if>

</body>
</html>




