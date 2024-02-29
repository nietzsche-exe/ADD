<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.HibernateUtils" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="modelo.Actor" %>
<%@ page import="modelo.Direccion" %>

<jsp:useBean id="actor" scope="request" class="modelo.Actor"/>
<jsp:setProperty name="actor" property="codigo_actor" param="cod_actor"/>

<%
    modelo.Actor actorBean = (modelo.Actor) request.getAttribute("actor");
   
		EntityManager em = HibernateUtils.getEmf().createEntityManager();
		actorBean = em.find(modelo.Actor.class, actorBean.getCodigo_actor());

    try {

        if (actorBean != null) {
        	em.getTransaction().begin();
            em.remove(actorBean);
            em.getTransaction().commit();
        }
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Borrado de actor</title>
</head>
<body>
<%
    
    if (actorBean != null) {
%>
    <h1>El actor <%=actorBean.getCodigo_actor() %> ha sido eliminado exitosamente</h1>
<%
    } else {
%>
    <h1>Error: No se pudo eliminar el actor</h1>
<%
    }
%>
<a href="Controlador">Volver al listado inicial</a>
</body>
</html>