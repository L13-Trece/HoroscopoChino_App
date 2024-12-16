<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consulta Horoscopo Chino</title>
</head>

<body>

<h1>Consulta tu Horoscopo Chino</h1>

<form action = "Consulta HoroscopoChino" method="GET">
<label for = "year">Ingresa tu año de nacimiento: </label>
<input type = "number" id="year" name = "year" required>
<button type ="submit">Consultar</button>
</form>

<c:if test = "$ {not empty horoscopo}">
<h2>Tu Horoscopo Chino es: ${horoscopo}</h2>
</c:if>
</body>
</html>