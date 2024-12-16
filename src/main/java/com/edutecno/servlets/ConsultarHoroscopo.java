package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ConsultarHoroscopo")
public class ConsultarHoroscopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String yearStr = request.getParameter("year");
		
		if (yearStr != null && ! yearStr.isEmpty()) {
			try {
				int year = Integer.parseInt(yearStr);
				String horoscopo = obtenerHoroscopoChino(year);
				request.setAttribute("horoscopo", horoscopo);
				request.getRequestDispatcher("consultaDeHoroscopoChino.jsp").forward(request, response);
				
			} catch (NumberFormatException e) {
				request.setAttribute("error", "El año debe ser un numero valido.");
				request.getRequestDispatcher("consultaDeHoroscopoChino.jsp").forward(request, response);
			}
		
	}else {
		 request.setAttribute("error","Debe proporcionar un año para consultar el horoscopo.");
		 request.getRequestDispatcher("consultaDeHoroscopoChino.jsp").forward(request, response);
	}
 }		

 private String obtenerHoroscopoChino (int year) {
	String [] signos = {"Rata", "Buey", "Tigre", "Conejo", "Dragon", "Serpiente", "Caballo", "Cabra", "Mono", "Gallo", "Perro", "Cerdo"};
	return signos [year % 12];
 }
}
