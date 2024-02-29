<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta de nuevo actor</title>
</head>
<body>
 <form method="POST" action="Controlador">
 	<table>
 		<tr>
 			<td>
 				<input type="hidden" name="opcion" value="insertarActor">
 				Nombre de actor: <input name="nomActor" required type="text" size="20" maxlength="20">
 				Calle del actor: <input name="calleActor" required type="text" size="50" maxlength="100">
 				Numero de calle del actor: <input name="numCalleActor" required type="number" max="255">
 			</td>
 		</tr>
 		<tr>
 			<td>
 				<input type="submit" value="enviar"/>
 				<input type="reset" name="cancelar" value="cancelar"/>
 			</td>
 		</tr>
 	</table>
 </form>
 <p/>
 <p/>
 <p/>
 <a href="Controlador"> Volver al listado inicial</a>
</body>
</html>