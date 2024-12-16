package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/ModificarUsuario")

public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String username = request.getParameter("id");
		
		if (username != null && ! username.isEmpty()) {
		
			Usuario usuario = usuarioDAO.buscarUsuarioPorNombre(username);
			
			if (usuario != null) {
				
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("modificacionDeUsuario.jsp").forward(request, response);
				
			} else {
				
				request.setAttribute("error", "Usuario no encontrado.");
				response.sendRedirect("listarUsuarios.jsp"); 
			}
		}else {
			
			request.setAttribute("error", "No proporciono un ID de usuario valido.");
			response.sendRedirect("listarUsuarios.jsp");
		}
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		String idStr = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String fechaNacimientoStr = request.getParameter("fechaNacimiento");
		String animal = request.getParameter("animal");
		
		if (idStr == null || idStr.isEmpty() ||
			nombre == null || nombre.isEmpty() ||
			username == null || username.isEmpty() ||
			password == null || password.isEmpty() ||
			email == null || email.isEmpty() ) {
			
			request.setAttribute("error", "Todos los campos son obligatorios.");
			request.getRequestDispatcher("modificacionDeUsuario.jsp").forward(request, response);
			return;
		}
		
		int id;
		try {
			id= Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			request.setAttribute("error", "ID invalido.");
			request.getRequestDispatcher("modificacionDeUsuario.jsp").forward(request, response);
			return;
		}
		
		
		Date fechaNacimiento = null;
		if (fechaNacimientoStr != null && ! fechaNacimientoStr.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
				fechaNacimiento = sdf.parse(fechaNacimientoStr);
			} catch (ParseException e) {
				request.setAttribute("error","Formato de fecha incorrecto.");
				request.getRequestDispatcher("modificacionDeUsuario.jsp").forward(request, response);
			}
		}
		  
		
		Usuario usuario = new Usuario (id, nombre, username, password, email, fechaNacimiento, animal);
			
		boolean usuarioModificado = false;
		
		try {
			usuarioModificado = usuarioDAO.modificarUsuario(usuario);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if (usuarioModificado) {
			response.sendRedirect("listarUsuarios.jsp?mensaje=Usuario modificado exitosamente");
			
		} else {
			
			request.setAttribute("error", "No se pudo modificar el usuario. Intentalo de nuevo.");
			request.getRequestDispatcher("modificacionDeUsuario.jsp").forward(request, response);
		}
	}

}






