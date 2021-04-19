package com.example.demo.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Service;

@Service
@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpersona")
	private int id;
	private String nombre;
	private String apellido;
	private char sexo;
	private boolean active;
	
	public Persona() {
		
	}
	
	public Persona(int id, String nombre, String apellido, char sexo, boolean active) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", active=" + active + "]";
	}
	
}
