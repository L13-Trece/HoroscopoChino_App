package com.edutecno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.ConexionBD;

public class HoroscopoDAOImp implements HoroscopoDAO {

       @Override
       public List<Horoscopo> obtenerHoroscopo() {
    	   List<Horoscopo> horoscopo = new ArrayList<>();
    	   String consultaSql = "SELECT * FROM horoscopo";
    	   
    	   try (Connection conn = ConexionBD.getConnection();
    		   PreparedStatement pstm = conn.prepareStatement (consultaSql);
    		   ResultSet rs = pstm.executeQuery()) {
    		   
    	   while (rs.next()) {
   
    		   Horoscopo h = new Horoscopo (rs.getString("animal"), 
    				                        rs.getDate("fecha_inicio"), 
    				                        rs.getDate("fecha_fin"));
    		   horoscopo.add(h);
    	   }
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    	   }
    	   
    	   return horoscopo;
    	   
       }
	
}
