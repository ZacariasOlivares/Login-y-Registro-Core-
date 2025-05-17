package com.zacarias.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zacarias.modelos.Usuario;
import com.zacarias.repositorios.RepositorioUsuario;

@Service
public class ServicioUsuario {
	// Inyeccion repositorio
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	public ServicioUsuario(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}
	
	public List<Usuario> obtenerTodosLosUsuarios() {
		return repositorioUsuario.findAll();
	}
	
	public Usuario obtenerUsuariosPorId(Long idUsuario) {
		return repositorioUsuario.findById(idUsuario).orElse(null);	
	}
	
	public Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
		return repositorioUsuario.findByNombreUsuario(nombreUsuario);
	}
	
	public Usuario agregarUsuario(Usuario usuarioNuevo) {
		return repositorioUsuario.save(usuarioNuevo);
	}
}
