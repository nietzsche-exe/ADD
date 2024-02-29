<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="modelo.HibernateUtils"%>
<%@page import="jakarta.persistence.EntityManager" %>
<%@page import="jakarta.persistence.EntityManagerFactory" %>
<%@page import="jakarta.persistence.Persistence" %>
<%@page import="jakarta.persistence.EntityTransaction" %>

<!-- Vamos a usar un javaBean para recuperar la información del actor que queremos mostrar -->
<jsp:useBean id="actor" scope="request" class="modelo.Actor"/>
<jsp:useBean id="direccion" scope="request" class="modelo.Direccion"/>

<jsp:setProperty name="actor" property="nombre_actor" param="nomActor"/>

<jsp:setProperty name="direccion" property="calle_direccion" param="calleActor"/>
<jsp:setProperty name="direccion" property="numero_direccion" param="numCalleActor"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta actor </title>
</head>
<body>

<%
	modelo.Actor actorBean = (modelo.Actor) request.getAttribute("actor");
	modelo.Direccion dirBean = (modelo.Direccion) request.getAttribute("direccion");
	
	if (actorBean != null && dirBean != null)
	{
		EntityManager em = HibernateUtils.getEmf().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		actorBean.setDireccion_actor(dirBean);
		
		try
		{
			trans.begin();
			em.persist(actorBean); // persisto en cascada actor y su dirección
			trans.commit();
		%>
		<h1>Creado nuevo actor</h1>
		<%
		}
		catch (Exception e)
		{
			trans.rollback();
			e.printStackTrace();
		}
	}
%>	
<p/>
<p/>
<p/>
<p/>
<a href="Controlador"> Volver al listado inicial</a>

</body>
</html>