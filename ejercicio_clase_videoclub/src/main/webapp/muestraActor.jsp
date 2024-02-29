<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="modelo.HibernateUtils"%>
<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="jakarta.persistence.EntityManagerFactory" %>
<%@page import="jakarta.persistence.Persistence" %>

<!-- Vamos a usar un javaBean para recuperar la información del actor que queremos mostrar -->
<jsp:useBean id="actor" scope="request" class="modelo.Actor"/>

<!-- mediante introspección se asigna a la campo clave el valor del parámetro cod_actor -->
<jsp:setProperty name="actor" property="codigo_actor" param="cod_actor"/>

<%
	// con getAttribute recupero desde Java el bean previamente creado (si existe) o NULL si no existe
	modelo.Actor actorBean = (modelo.Actor) request.getAttribute("actor");
	
	if (actorBean != null)
	{
		EntityManager em = HibernateUtils.getEmf().createEntityManager();
		actorBean = em.find(modelo.Actor.class, actorBean.getCodigo_actor());
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muestra actor: <%=actorBean.getCodigo_actor() %></title>
</head>
<body>
	<%=actorBean.getCodigo_actor() %> - <%=actorBean.getNombre_actor() %> - <%=actorBean.getDireccion_actor().getCalle_direccion() %> - <%=actorBean.getDireccion_actor().getNumero_direccion() %>
	<p/>
	<p/>
	<p/>
	<p/>
	<a href="Controlador"> Volver al listado inicial</a>
	
</body>
</html>