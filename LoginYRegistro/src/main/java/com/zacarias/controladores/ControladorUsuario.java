package com.zacarias.controladores;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.zacarias.modelos.LoginUsuario;
import com.zacarias.modelos.Usuario;
import com.zacarias.servicios.ServicioUsuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
								   @ModelAttribute("loginUsuario") LoginUsuario usuarioLogin,
								   HttpSession sesion) {
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
		
		sesion.setAttribute("idUsuario", usuarioNuevo.getId());
		sesion.setAttribute("nombreUsuario", usuarioNuevo.getNombreUsuario());
		sesion.setAttribute("nombre", usuarioNuevo.getNombre());
		sesion.setAttribute("apellido", usuarioNuevo.getApellido());
		sesion.setAttribute("correo", usuarioNuevo.getCorreo());
		sesion.setAttribute("fechaNacimiento", usuarioNuevo.getFechaNacimiento());
		
		return "redirect:/inicio";
	}
	
	@PostMapping("/procesa/login")
	public String loginUsuario(@Valid @ModelAttribute("loginUsuario") LoginUsuario usuarioLogin,
							   BindingResult validaciones,
							   @ModelAttribute("usuario") Usuario usuario,
							   HttpSession sesion) {
		
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
		sesion.setAttribute("idUsuario", usuarioActual.getId());
		sesion.setAttribute("nombreUsuario", usuarioActual.getNombreUsuario());
		sesion.setAttribute("nombre", usuarioActual.getNombre());
		sesion.setAttribute("apellido", usuarioActual.getApellido());
		sesion.setAttribute("correo", usuarioActual.getCorreo());
		sesion.setAttribute("fechaNacimiento", usuarioActual.getFechaNacimiento());
		return "redirect:/inicio";
	}
	
	@GetMapping("/inicio")
	public String desplegarInicio(HttpSession sesion) {
		if (sesion.getAttribute("idUsuario") == null) {
			return "redirect:/";
		}
		return "inicio.jsp";
	}
	
	@PostMapping("/procesa/logout")
	public String cerrarSesion(HttpSession sesion) {
		
		sesion.invalidate();
		
		return "redirect:/";
	}
	
	
}
