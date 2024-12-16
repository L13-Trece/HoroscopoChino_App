package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


 
@WebServlet ("/IniciarSesion")
		
public class IniciarSesion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
       
	
	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("inicioDeSesion.jsp").forward(request, response);
	}

	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			request.setAttribute("error", "Debe completar todos los campos.");
			request.getRequestDispatcher("inicioDeSesion.jsp").forward(request, response);
			return;
		}
		
		Usuario usuario = usuarioDAO.buscarUsuario(username,password);
		
		if (usuario != null) {

			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			response.sendRedirect("inicioDeSesion.jsp");
			
		} else {
			
			request.setAttribute("error","credenciales incorrectas");
			request.getRequestDispatcher("inicioDeSesion.jsp").forward (request, response);
		}
	}
}




