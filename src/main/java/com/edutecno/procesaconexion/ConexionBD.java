package com.edutecno.procesaconexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionBD {

	    public static Connection getConnection() {
	        Connection conn = null;
	        
	        try {
	        	Properties props = new Properties();
	        	props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.factory.BeanFactory");
	        	props.setProperty(Context.PROVIDER_URL, "localhost:9090");
	        	
	            InitialContext ctx = new InitialContext(props);	            
	            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Horoscopo");	     
	            conn = ds.getConnection();
	            System.out.println("Conexion exitosa");
	            
	        } catch (NamingException e) {
	        	System.err.println ("Error al encontrar el recurso JNDI: " + e.getMessage());
	            e.printStackTrace(); 
	            
	        } catch (SQLException e) {
	        	System.err.println("Error al obtener la conexion: " + e.getMessage());
	        	e.printStackTrace();
	        }
	        return conn;
	    }
	 
	    
	    public static void main(String[] args) {
	        Connection connection = getConnection();
	        
	        
	        if (connection != null) {
	        	try {
	        		String query = "SELECT * FROM zodiaco";
	        		
	        		Statement stmt = connection.createStatement();
	        		ResultSet rs = stmt.executeQuery(query);
	        		
	        		while (rs.next()) {
	        			String animal = rs.getString("animal");
	        			java.sql.Date fechaInicio = rs.getDate("fecha_inicio");
	        			System.out.println("Animal: " + ", Fecha de inicio: " + fechaInicio);
	        		}
	        		
	        		rs.close();
	        		stmt.close();
	        		
	        	} catch (SQLException e) {
	        		System.err.println("Error al ejecutar la consulta: " + e.getMessage());
	        		e.printStackTrace();
	        		
	        	} finally {
	        		try {
	        			if (connection != null && ! connection.isClosed()) {
	        				connection.close();
	        				System.out.println("Conexion cerrada.");
	        			}
	        		
	        	} catch (SQLException e) {
	        		System.err.println("Error al cerrar la conexion: " + e.getMessage());
	        		e.printStackTrace();
	        	}
	        } 
	        
	    }else {
        	System.out.println ("No se pudo establecer la conexion.");
	    }
	}
}
	   




