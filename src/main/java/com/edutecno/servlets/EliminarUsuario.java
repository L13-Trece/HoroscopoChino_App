package com.edutecno.servlets;

import com.edutecno.dao.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/EliminarUsuario")
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
       
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userIdStr = request.getParameter("userId");
		
		if (userIdStr != null && ! userIdStr.isEmpty()) {
			try {
				int userId = Integer.parseInt(userIdStr);
				
				boolean eliminado = usuarioDAO.eliminarUsuario (userId);
				
				if (eliminado) {
					response.sendRedirect("listarUsuarios.jsp?mensaje=Usuario eliminado exitosamente.");
					
				} else {
					request.setAttribute("error", "No se pudo eliminar el usuario. Intentalo nuevamente.");
					request.getRequestDispatcher("eliminacionDeUsuarios.jsp").forward(request, response);
				
				}
				
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID de usuario no valido.");
				request.getRequestDispatcher("eliminacionDeUsuarios.jsp").forward(request, response);
			}
				
	} else {
		request.setAttribute ("error", "ID de usuario no valido.");
		request.getRequestDispatcher("eliminacionDeUsuarios.jsp").forward(request, response);
	}
}

}
