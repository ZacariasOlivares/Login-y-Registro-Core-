<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Inicio</title>
	</head>
	<body>
		<h1>Bienvenid@ de vuelta a la aplicación de Login y Registro</h1>
		<p>id = ${idUsuario}</p>
		<p>Nombre Usuario = ${nombreUsuario}</p>
		<p>Nombre = ${nombre}</p>
		<p>Apellido = ${apellido}</p>
		<p>Correo = ${correo}</p>
		<p>Fecha Nacimiento = ${fechaNacimiento}</p>
		
		<form action="/procesa/logout" method="POST">
			<button>Cerrar sesion</button>
		</form>
	</body>
</html>