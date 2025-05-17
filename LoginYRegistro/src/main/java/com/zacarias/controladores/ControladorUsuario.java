package com.zacarias.controladores;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.zacarias.modelos.LoginUsuario;
import com.zacarias.modelos.Usuario;
import com.zacarias.servicios.ServicioUsuario;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorUsuario {
	// Inyeccion sel servicio
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	public ControladorUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}
	
	// Metodos
	@GetMapping("/")
	public String desplegarFormularios(@ModelAttribute("usuario") Usuario usuario,
									   @ModelAttribute("loginUsuario") LoginUsuario usuarioLogin) {
		return "index.jsp";
	}
	
	@PostMapping("/procesa/registro")
	public String registrarUsuario(@Valid @ModelAttribute("usuario") Usuario usuarioNuevo,
								   BindingResult validaciones, 
								   @ModelAttribute("loginUsuario") LoginUsuario usuarioLogin) {
		String nombreUsuario = usuarioNuevo.getNombreUsuario();
		Usuario usuarioActual = this.servicioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
		if (!usuarioNuevo.getContrasenia().equals(usuarioNuevo.getConfirmacionContrasenia())) {
			validaciones.rejectValue("confirmacionContrasenia", "ErrorContraseña", "Las contraseñas no coinciden");
		}
		
		if (!(usuarioActual == null)) {
			validaciones.rejectValue("nombreUsuario", "ErrorNombreUsuario", "Nombre de usuario existente");
		}
		if (validaciones.hasErrors()) {
			return "index.jsp";
		}
		
		String contraseñaEncriptada = BCrypt.hashpw(usuarioNuevo.getContrasenia(), BCrypt.gensalt());
		
		usuarioNuevo.setContrasenia(contraseñaEncriptada);
		this.servicioUsuario.agregarUsuario(usuarioNuevo);
		return "inicio.jsp";
	}
	
	@PostMapping("/procesa/login")
	public String loginUsuario(@Valid @ModelAttribute("loginUsuario") LoginUsuario usuarioLogin,
							   BindingResult validaciones,
							   @ModelAttribute("usuario") Usuario usuario) {
		
		String nombreUsuario = usuarioLogin.getNombreUsuarioLogin();
		Usuario usuarioActual = this.servicioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
		if (usuarioActual == null) {
			validaciones.rejectValue("nombreUsuarioLogin", "ErrorNombreUsuario", "Nombre de usuario incorrecto");
		}else {
			if (!BCrypt.checkpw(usuarioLogin.getContraseniaLogin(), usuarioActual.getContrasenia())) {
				validaciones.rejectValue("contraseniaLogin", "ErrorContraseña", "Contraseña Incorrecta");
			}
		}
		if (validaciones.hasErrors()) {
			return "index.jsp";

		}
		return "inicio.jsp";
	}
	
}
