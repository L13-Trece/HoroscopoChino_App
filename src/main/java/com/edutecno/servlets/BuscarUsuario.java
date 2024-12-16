package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/BuscarUsuarios")
public class BuscarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");

		if (username != null && ! username.isEmpty()) {
			Usuario usuario = usuarioDAO.buscarUsuarioPorNombre(username);
			
			if (usuario != null) {
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
				
		} else {
			request.setAttribute("error", "Usuario no encontrado.");
			request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
		}
			
	} else {
		request.setAttribute("error", "Debe proporcionar un nombre de usuario..");
		request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
	}

}

}


