package com.example.demo.restController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Modelo.IPersonaDao;
import com.example.demo.Modelo.Persona;
import com.example.demo.Modelo.PersonaDao;

@RestController
@RequestMapping("/api")
public class RestControllerClass {

	@Autowired
	private IPersonaDao personaDao;
	
	@Autowired
	private Persona persona;

//	Metodos de la api Rest
	@GetMapping("/listarPersonas")
	public ResponseEntity<ArrayList<Persona>> listarPersonas() {
		ArrayList<Persona> lista = personaDao.listarPersonas();
		if(lista != null && lista.size() > 0) {
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
		int resultado = personaDao.agregarPersona(persona);
		if(resultado > 0) {
			return ResponseEntity.ok("Usuario: "+ nombre +" agregado");
		}else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/actualizarPersona/{id}/{nombre}/{apellido}/{sexo}/{active}")
	public ResponseEntity<String> actualizarPersona(@PathVariable("id") int id , @PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido, @PathVariable("sexo") char sexo, @PathVariable("active") boolean active) {
		persona.setId(id);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		persona.setSexo(sexo);
		persona.setActive(active);
		int resultado = personaDao.actualizarPersona(persona);
		if(resultado > 0 ) {
			return ResponseEntity.ok("Usuario ID: "+ id +" - "+ nombre +" actualizado");
		}else {
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping("/eliminarPersona/{id}")
	public ResponseEntity<String> eliminarPersona(@PathVariable("id") int id) {
		int resultado = personaDao.eliminarPersona(id);
		if(resultado > 0) {
			return ResponseEntity.ok("Usuario ID: "+ id + " eliminado");
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
//	Metodos random
	@GetMapping({"","/"})
	public String index() {
		return "Raiz de la api";
	}
	
//	Metodo para utilizar en todos los metodos del controlador (generalmente se utilizan para llenar select de html5 o cualquier otro elemento.)
	@ModelAttribute("listaFormulario")
	public List<String> listaFormulario(Model model) {
		List<String> listaFormulario = new ArrayList<String>();
		listaFormulario.add("Informática");
		listaFormulario.add("Contabilidad");
		listaFormulario.add("Recursos Humanos");
		listaFormulario.add("Otro");
		return listaFormulario;
	}
	
//	Wiki
	
// 	El autowired de arriba me evita tener que instanciar la clase PersonaDao y ocupar sus metodos mas facilmente (Inyeccion de dependencias)
// 	PersonaDao personaDao = new PersonaDao();
// 	Para hacer uso de esta función tengo que agregar la anotacion @Service o @Component en las clases a inyectar. (service y component hacen lo mismo en la practica)

//	@GetMapping("/listarPersonas")
//	public ArrayList<Persona> listarPersonas() {
//		return personaDao.listarPersonas();
//	}

//	Un metodo con mas rutas
//	@GetMapping({"/listarPersonas","/otraRuta"})
//	public ResponseEntity<ArrayList<Persona>> diferentesRutas() {
//		ArrayList<Persona> lista = personaDao.listarPersonas();
//		if(lista != null && lista.size() > 0) {
//			return ResponseEntity.ok(lista);
//		} else {
//			return ResponseEntity.noContent().build();
//		}
//	}
	
//	Pasar datos a la vista
//	@GetMapping("/index")
//	public String pasarDatosVista(Model model) {
//		model.addAttribute("titulo","Valor del titulo que aparecera en el HTML de la vista");
//		return "index";
//	}

//	DEPLOY DEL PROGRAMA EN LOCAL
//	1.Crear JAVA_HOME en variables de entornos (indicar home de jdk 11)
//	2.CD A LA RUTA RAIZ DEL PROYECTO Y EJECUTAR
//	mvnw package
//	3.CD TARGET
//	java -jar "nombreDeProyecto.jar"
//	Esto lo levanta en local.


}
