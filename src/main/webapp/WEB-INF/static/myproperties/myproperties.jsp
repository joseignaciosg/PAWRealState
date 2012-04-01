<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Mis propiedades</h3>
<hr />
<table class="table table-striped">
	<thead>
		<tr>
			<td>Barrio</td>
			<td>Direccion</td>
			<td>Precio</td>
			<td>Acciones</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="property" items="${ current_user.properties }">
			<tr>
				<td>${ property.neighborhood }</td>
				<td>${ property.address }</td>
				<td>${ property.price }</td>
				<td>Ver publicación - Editar - Dar de baja - Eliminar</td>	
			</tr>
		</c:forEach>
	</tbody>
</table>
