package com.example.demo.restController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Modelo.Persona;
import com.example.demo.Modelo.PersonaDao;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	// El autowired de arriba me evita tener que instanciar la clase PersonaDao y ocupar sus metodos mas facilmente (Inyeccion de dependencias)
	// PersonaDao personaDao = new PersonaDao();
	// Para hacer uso de esta función tengo que agregar la anotacion @Service en las clases a inyectar.
	
	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private Persona persona;

	@GetMapping("/listarPersonas")
	public ArrayList<Persona> listarPersonas() {
		return personaDao.listarPersonas();
	}

	@PostMapping("/agregarPersona/{nombre}/{apellido}/{sexo}/{active}")
	public void agregarPersona(@PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido, @PathVariable("sexo") char sexo, @PathVariable("active") boolean active) {
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setSexo(sexo);
		persona.setActive(active);
		personaDao.agregarPersona(persona);
	}

	@PutMapping("/actualizarPersona/{id}/{nombre}/{apellido}/{sexo}/{active}")
	public void actualizarPersona(@PathVariable("id") int id , @PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido, @PathVariable("sexo") char sexo, @PathVariable("active") boolean active) {
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setSexo(sexo);
		persona.setActive(active);
		personaDao.actualizarPersona(persona);
	}

	@DeleteMapping("/eliminarPersona/{id}")
	public void eliminarPersona(@PathVariable("id") int id) {
		personaDao.eliminarPersona(id);
	}

}
