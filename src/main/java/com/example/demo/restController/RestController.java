package com.example.demo.restController;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ArrayList<Persona>> listarPersonas() {
		ArrayList<Persona> lista = personaDao.listarPersonas();
		if(lista != null) {
			return ResponseEntity.ok(lista);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("/agregarPersona/{nombre}/{apellido}/{sexo}/{active}")
	public ResponseEntity<String> agregarPersona(@PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido, @PathVariable("sexo") char sexo, @PathVariable("active") boolean active) {
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setSexo(sexo);
		persona.setActive(active);
		personaDao.agregarPersona(persona);
		return ResponseEntity.ok("Usuario agregado");
	}

	@PutMapping("/actualizarPersona/{id}/{nombre}/{apellido}/{sexo}/{active}")
	public ResponseEntity<String> actualizarPersona(@PathVariable("id") int id , @PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido, @PathVariable("sexo") char sexo, @PathVariable("active") boolean active) {
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setSexo(sexo);
		persona.setActive(active);
		personaDao.actualizarPersona(persona);
		return ResponseEntity.ok("Usuario actualizado");
	}

	@DeleteMapping("/eliminarPersona/{id}")
	public ResponseEntity<String> eliminarPersona(@PathVariable("id") int id) {
		personaDao.eliminarPersona(id);
		return ResponseEntity.ok("Usuario eliminado");
	}
	
	
//  Metodos antiguos
//	@GetMapping("/listarPersonas")
//	public ArrayList<Persona> listarPersonas() {
//		return personaDao.listarPersonas();
//	}

}
