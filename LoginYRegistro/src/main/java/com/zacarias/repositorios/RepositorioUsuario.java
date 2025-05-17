package com.zacarias.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zacarias.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findAll();
	
	Usuario findByNombreUsuario(String nombreUsuario);
}
