package com.zacarias.modelos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUsuario {
	
	@Size(min = 3, max = 15, message = "El nombre de usuario debe tener una longitud entre 3 y 15 caracteres")
	private String nombreUsuarioLogin;
	
	@NotBlank(message = "Por favor proporciona la constraseña.")
	private String contraseniaLogin;
	
	// Constructor
	public LoginUsuario() {

	}

	// Getters y Setters
	public String getNombreUsuarioLogin() {
		return nombreUsuarioLogin;
	}

	public void setNombreUsuarioLogin(String nombreUsuarioLogin) {
		this.nombreUsuarioLogin = nombreUsuarioLogin;
	}

	public String getContraseniaLogin() {
		return contraseniaLogin;
	}

	public void setContraseniaLogin(String contraseniaLogin) {
		this.contraseniaLogin = contraseniaLogin;
	}
	
	

	
}
