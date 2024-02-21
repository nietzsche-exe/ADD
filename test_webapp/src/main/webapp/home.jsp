<%@page import="modelo.HibernateUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="jakarta.persistence.Query" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aplicacion web Videoclub</title>
</head>
<body>
	<form action="Controlador", name="frm_muestraActores", method="POST"></form>
		<input type="hidden", name="opcion" value="muestraActor">
		<input type="hidden", name="cod_actor">
		<table border="1">
			<tr>
				<td colspan="2">lista Actores</td>
			</tr>
			<%! @SuppressWarnings("unchecked") %>
			<%
			 EntityManager em = HibernateUtils.getEmf().createEntityManager();
			try {
                 
				Query consulta = em.createQuery("FROM Actor ORDER BY codigo_actor");
				List<modelo.Actor> listaActores = consulta.getResultList();
				
				if (listaActores.isEmpty()) {
					
					%>
					<tr> <td colspan="2">No hay actores</td> </tr>
		<%
					
				}
				else {
				
					for (modelo.Actor actor : listaActores) {
                        
                        %>
						<tr>
							<td><%=actor.getCod_act()%></td>
							<td><%=actor.getNombre()%></td>
							<td><a
								href="Controlador?opcion=muestraActor&cod_actor=<%=actor.getCod_act()%>">Ver</a></td>
							<td><a
								href="Controlador?opcion=eliminaActor&cod_actor=<%=actor.getCod_act()%>">Eliminar</a></td>
						</tr>
						<%
                    }
					
				}
				
            } catch (Exception e) {
            	
            }
			
			%>
	</table>
</body>
</html>