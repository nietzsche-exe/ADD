package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		
		String operacion = request.getParameter("opcion");
		
		if (operacion == null) {
			operacion = "";
		}
		
		switch (operacion) {
		case "muestraActor":
			break;
		case "altaActor":
			break;
		case "insertarActor":
			break;
		default:
			response.sendRedirect("home.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		procesarPeticion(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		procesarPeticion(request, response);
		
	}

}
