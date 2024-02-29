<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="jakarta.persistence.Query" %>
<%@page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aplicación web Videoclub</title>
</head>
<body>
	<form name="frm_muestraActores" method="POST" action="Controlador">
		<table border="1">
			<tr>
				<td colspan="2"> Directores y peliculas </td>
			</tr>
			
			<%! @SuppressWarnings("unchecked") %>
			<%
				EntityManager em = modelo.HibernateUtils.getEmf().createEntityManager();
				try
				{
					Query consulta = em.createQuery("FROM Pelicula ORDER BY director");
					List<modelo.Pelicula> peliculas = consulta.getResultList();
					
					if (peliculas.isEmpty())
					{
					
						%>
						<tr> <td colspan="2"> No existen directores </td></tr>
						<%

					}
					else
					{
						for(modelo.Pelicula pelicula: peliculas)
						{
							%>
							<tr> 
								
								<td> <%=pelicula.getTitulo() %> </td>
								<td> <%=pelicula.getFechaGrabacion() %> </td>
							</tr>
							
							
							<%
						}
					}
					
				}
				catch (Exception e)
				{
					e.printStackTrace();	
				}
			%>
			
			
		</table>
		</p>
		</p>
		<tr>
			<td><a href="index.html">Volver pagina principal</a></td>
		</tr>
	</form>
</body>
</html>