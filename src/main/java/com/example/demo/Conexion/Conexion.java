package com.example.demo.Conexion;

import java.sql.*;

public abstract class Conexion {
	
	public static Connection retornarConexion() {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_springboot","root","sasa");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
