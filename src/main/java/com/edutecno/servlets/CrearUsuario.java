package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/CrearUsuario")

public class CrearUsuario<Int> extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UsuarioDAO usuarioDAO= new UsuarioDAO();

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
	}

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		int id = 0;
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			id = 0;
		}

		String nombre = request.getParameter("nombre");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Date fechaNacimiento = null;
		String animal = request.getParameter("animal");
		
		String fechaParam = request.getParameter("fechaNacimiento");
		if (fechaParam != null && ! fechaParam.isEmpty()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");
				fechaNacimiento = sdf.parse(fechaParam);
			} catch (ParseException e) {
				request.setAttribute("error", "Formato de fecha incorrecto.");
				request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
				return;
			}
		}
		
		
		if (username == null || username.isEmpty() ||
				password == null || password.isEmpty() ||
				email == null || email.isEmpty()) {
			
			request.setAttribute("error", "Todos los campos son obligatorios.");
			request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
			return;
		}
		
		Usuario nuevoUsuario = new Usuario(id, nombre, username, password, email, fechaNacimiento, animal);	
		
		boolean usuarioCreado = usuarioDAO.crearUsuario (nuevoUsuario);
		
		if (usuarioCreado) {			
			request.setAttribute("mensaje", "Usuario creado exitosamente! ¡Inicia Sesion!");
			request.getRequestDispatcher("inicioDeSesion.jsp").forward(request, response);
			
		} else {			
			request.setAttribute("error", "No se puede crear el usuario, Intentalo de nuevo.");
			request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
 	}
	}
}





