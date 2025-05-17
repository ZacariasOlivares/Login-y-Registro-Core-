<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login y registro</title>
	</head>
	<body>
		<div class="contenedor-main">
			<div class="formulario">
				<form:form action="/procesa/registro" method="POST" modelAttribute="usuario">
					<h1>Registro</h1>
					<div class="formulario-input">
						<form:label path="nombre">Nombre:</form:label>
						<form:input path="nombre" type="text" />
						<form:errors path="nombre"/>
					</div>
					<div class="formulario-input">
						<form:label path="apellido">Apellido:</form:label>
						<form:input path="apellido" type="text"/>
						<form:errors path="apellido"/>
					</div>
					<div class="formulario-input">
						<form:label path="nombreUsuario">Nombre de Usuario:</form:label>
						<form:input path="nombreUsuario" type="text"/>
						<form:errors path="nombreUsuario"/>
					</div>
					<div class="formulario-input">
						<form:label path="correo">Correo: </form:label>
						<form:input path="correo" type="email"/>
						<form:errors path="correo"/>
					</div>
					<div class="formulario-input">
						<form:label path="fechaNacimiento">Fecha Nacimiento: </form:label>
						<form:input path="fechaNacimiento" type="date"/>
						<form:errors path="fechaNacimiento"/>
					</div>
					<div class="formulario-input">
						<form:label path="contrasenia">Contraseña: </form:label>
						<form:input path="contrasenia" type="password"/>
						<form:errors path="contrasenia"/>
					</div>
					<div class="formulario-input">
						<form:label path="confirmacionContrasenia">Confirmacion contraseña: </form:label>
						<form:input path="confirmacionContrasenia" type="password"/>
						<form:errors path="confirmacionContrasenia"/>
					</div>
					<div>
						<button>Registrarse</button>
					</div>
				</form:form>
			</div>
			<div class="formulario">
				<form:form action="/procesa/login" method="POST" modelAttribute="loginUsuario">
					<h1>Login</h1>
					<div class="formulario-input">
						<form:label path="nombreUsuarioLogin">Nombre de Usuario:</form:label>
						<form:input path="nombreUsuarioLogin" type="text"/>
						<form:errors path="nombreUsuarioLogin"/>
					</div>
					<div class="formulario-input">
						<form:label path="contraseniaLogin">Contraseña: </form:label>
						<form:input path="contraseniaLogin" type="password"/>
						<form:errors path="contraseniaLogin"/>
					</div>
					<div>
						<button>Iniciar sesión</button>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>