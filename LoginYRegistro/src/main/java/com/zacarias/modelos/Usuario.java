package com.zacarias.modelos;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_usuario", unique = true)
	@NotBlank(message = "Por favor proporciona el nombre de usuario.")
	@Size(min = 3, max = 15, message = "El nombre de usuario debe tener una longitud entre 3 y 15 caracteres")
	private String nombreUsuario;
	
	@NotBlank(message = "Por favor proporciona la constraseña.")
	@Size(min = 8, message = "La contraseña necesita tener al menos 8 catacteres.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$", message = "La contraseña debe incluir minúsculas, mayúsculas y un número")
	private String contrasenia;
	
	@Transient
	private String confirmacionContrasenia;
	
	@NotBlank(message = "Por favor proporciona un correo.")
	@Email(message = "Proporciona un correo válido")
	private String correo;
	
	@NotBlank(message = "Por favor proporciona tu nombre.")
	@Size(min = 3, message = "Longitud minima de 3 caracteres")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales")
	private String nombre;
	
	@Size(min = 3, message = "Longitud minima de 3 caracteres")
	@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre no debe contener números ni caracteres especiales")
	private String apellido;
	
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	@NotNull(message = "Proporciona tu fecha de nacimiento.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", updatable = false)
	private Date fechaCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;
	
	@PrePersist
	protected void OnCreate() {
		this.fechaCreacion = new Date();
		this.fechaActualizacion = new Date();
	}
	
	@PreUpdate
	protected void OnUpdate() {
		this.fechaActualizacion = new Date(); 
	}

	
	// Constructor
	public Usuario() {

	}
	
	// Getters y Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConfirmacionContrasenia() {
		return confirmacionContrasenia;
	}

	public void setConfirmacionContrasenia(String confirmacionContrasenia) {
		this.confirmacionContrasenia = confirmacionContrasenia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	
}
