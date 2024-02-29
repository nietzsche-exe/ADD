package controlador;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.setContentType("text/html;charset=UTF-8");
		
		String operacion = request.getParameter("opcion");
		
		if (operacion == null)
		{
			operacion = "";
		}
		
		RequestDispatcher rd;
		
		switch(operacion)
		{
			case "muestraActor":
				// es el servidor quien redirije internamente hacia la vista muestraActor.jsp
				rd = request.getRequestDispatcher("muestraActor.jsp");
				rd.forward(request, response);
				break;
			case "altaActor":
				// es el cliente quien deber� invocar a este recurso
				response.sendRedirect("frmNuevoActor.jsp");
				break;
			case "insertarActor":
				// es el servidor quien redirije internamente hacia la vista procNuevoActor.jsp
				rd = request.getRequestDispatcher("procNuevoActor.jsp");
				rd.forward(request, response);
				break;
			default:
				// es el cliente quien deber� invocar a este recurso
				response.sendRedirect("index.html");
	
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  
	{
		procesarPeticion(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  
	{
		procesarPeticion(request, response);
	}

}
