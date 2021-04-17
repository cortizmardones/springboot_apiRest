package com.example.demo.Modelo;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.Conexion.Conexion;

@Service
public class PersonaDao {

	private ArrayList<Persona> listaPersonas = new ArrayList<>();

	public ArrayList<Persona> listarPersonas() {
		try {
			listaPersonas.clear();
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persona");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Persona persona = new Persona();
				persona.setId(resultSet.getInt("idPersona"));
				persona.setNombre(resultSet.getString("nombre"));
				persona.setApellido(resultSet.getString("apellido"));
				persona.setSexo(resultSet.getString("sexo").charAt(0));
				persona.setActive(resultSet.getBoolean("active"));
				this.listaPersonas.add(persona);
			}

			connection.close();
			return listaPersonas;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int agregarPersona(Persona persona) {
		int resultado = 0;
		try {
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?)");
			preparedStatement.setString(1, null);
			preparedStatement.setString(2, persona.getNombre());
			preparedStatement.setString(3, persona.getApellido());
			preparedStatement.setString(4, String.valueOf(persona.getSexo()));
			if (persona.isActive() == true) {
				preparedStatement.setInt(5, 1);
			} else {
				preparedStatement.setInt(5, 0);
			}
			resultado = preparedStatement.executeUpdate();
			connection.close();
			System.out.println("*** Resultado AgregarPersona: " + resultado + " ***");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int actualizarPersona(Persona persona) {
		int resultado = 0;
		try {
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persona SET nombre='" + persona.getNombre() + "' , " + "apellido='" + persona.getApellido() + "', " + "sexo='" + persona.getSexo() + "', " + "active=" + persona.isActive() + " WHERE idPersona ='" + persona.getId() + "'");
			resultado = preparedStatement.executeUpdate();
			connection.close();
			System.out.println("*** Resultado ActualizarPersona: " + resultado + " ***");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public int eliminarPersona(int id) {
		int resultado = 0;
		try {
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persona WHERE idPersona='" + id + "'");
			resultado = preparedStatement.executeUpdate();
			connection.close();
			System.out.println("*** Resultado EliminarPersona: " + resultado + " ***");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

}
