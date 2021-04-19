package com.example.demo.Modelo;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("implementacionJPA")
public class PersonaDaoJPA implements IPersonaDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<Persona> listarPersonas() {
		ArrayList<Persona> lista = (ArrayList<Persona>) entityManager.createQuery("from Persona").getResultList();
		return lista;
	}

	@Override
	@Transactional
	public int agregarPersona(Persona persona) {
		entityManager.persist(persona);
		return 1;
	}

	@Override
	public int actualizarPersona(Persona persona) {
		return 0;
	}

	@Override
	public int eliminarPersona(int id) {
		return 0;
	}
	

}
