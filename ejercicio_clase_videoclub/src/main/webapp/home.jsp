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
		<input type="hidden" name="opcion" value="muestraActor">
		<input type="hidden" name="cod_actor">
		<table border="1">
			<tr>
				<td colspan="2"> lista de actores </td>
			</tr>
			
			<tr>
				<td colspan="2"> <a href="javascript:void(0)" onclick="javascript:document.frm_muestraActores.opcion.value='altaActor';document.frm_muestraActores.submit();"> Nuevo actor </a> </td>
			</tr>
			
			<%! @SuppressWarnings("unchecked") %>
			<%
				EntityManager em = modelo.HibernateUtils.getEmf().createEntityManager();
				try
				{
					Query consulta = em.createQuery("FROM Actor ORDER BY codigo_actor");
					List<modelo.Actor> actores = consulta.getResultList();
					
					if (actores.isEmpty())
					{
					
						%>
						<tr> <td colspan="2"> No existen actores </td></tr>
						<%

					}
					else
					{
						for(modelo.Actor actor: actores)
						{
							%>
							<tr> 
								<td> <%=actor.getCodigo_actor() %></td>
								<td> <a href="javascript:void(0)" onclick="javascript:document.frm_muestraActores.cod_actor.value='<%=actor.getCodigo_actor() %>';document.frm_muestraActores.submit();"> <%=actor.getNombre_actor() %> </a></td>
								<td> <a href="javascript:void(0)" onclick="javascript:document.frm_muestraActores.cod_actor.value='<%=actor.getCodigo_actor() %>';document.frm_muestraActores.opcion.value='altaActor'.submit();">Borrar</a></td>
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
		
	</form>
</body>
</html>