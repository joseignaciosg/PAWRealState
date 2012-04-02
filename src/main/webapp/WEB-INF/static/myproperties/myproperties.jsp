<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
				<td>$${ property.price }</td>
				<td>
					<div class="btn-group">
						<a href="${ basePath }/properties/view?id=${ property.id }" class="btn btn-mini">Ver publicación</a>
						<a href="${ basePath }/myproperties/edit?ID=${ property.id }" class="btn btn-mini">Editar</a>
						<a href="${ basePath }/myproperties/setDown?ID=${ property.id }" class="btn btn-mini">Dar de baja</a>
						<a href="${ basePath }/myproperties/delete?ID=${ property.id }" class="btn btn-mini btn-danger">Eliminar</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
