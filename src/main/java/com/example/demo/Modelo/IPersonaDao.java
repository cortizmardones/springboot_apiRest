package com.example.demo.Modelo;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface IPersonaDao {
	
	public ArrayList<Persona> listarPersonas();
	public int agregarPersona(Persona persona);
	public int actualizarPersona(Persona persona);
	public int eliminarPersona(Persona persona);
	
}
