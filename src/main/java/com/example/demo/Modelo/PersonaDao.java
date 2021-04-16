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
			
		    //Hago esto por si llamo el metodo desde el POSTMAN mas de una vez nop siga creciendo el json de respuesta. 
			listaPersonas.clear();
			
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM persona");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Persona persona = new Persona();
				persona.setId(Integer.parseInt(resultSet.getString("idPersona")));
				persona.setNombre(resultSet.getString("nombre"));
				persona.setApellido(resultSet.getString("apellido"));
				//Convertir char en String
				persona.setSexo(resultSet.getString("sexo").charAt(0));
				persona.setActive(Boolean.parseBoolean(resultSet.getString("active")));
				this.listaPersonas.add(persona);
			}
			
			connection.close();
			
			return listaPersonas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void agregarPersona(Persona persona) {
		try {	
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persona VALUES (?,?,?,?,?)");
			preparedStatement.setString(1,null);
			preparedStatement.setString(2,persona.getNombre());
			preparedStatement.setString(3,persona.getApellido());
			preparedStatement.setString(4,String.valueOf(persona.getSexo()));
			if(persona.isActive() == true) {
				preparedStatement.setInt(5,1);
			} else {
				preparedStatement.setInt(5,0);
			}
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actualizarPersona(Persona persona) {
		try {
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE persona SET nombre='" +persona.getNombre()+ "' , apellido='" +persona.getApellido()+ "', sexo='" +persona.getSexo()+ "' WHERE idPersona ='"+persona.getId()+ "'");
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarPersona(int id) {
		try {
			Connection connection = Conexion.retornarConexion();
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM persona WHERE idPersona='"+ id +"'");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
