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
			<td>Visibilidad</td>
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
					<c:if test="${ property.visible }">
						Visible
					</c:if>
					<c:if test="${ ! property.visible }">
						No visible
					</c:if>
				</td>
				
				<td>
					<div class="btn-group">
						<a href="${ basePath }/property/${ property.id }" class="btn btn-mini">Ver publicación</a>
						<a href="${ basePath }/property/${ property.id }/edit" class="btn btn-mini">Editar</a>
						<a href="${ basePath }/property/${ property.id }/photos" class="btn btn-mini">Fotos</a>
						<a href="${ basePath }/property/${ property.id }/changevisibility" class="btn btn-mini js-change-visibility" data-prop-id="${ property.id }">Cambiar visibilidad</a>
						<a href="${ basePath }/property/${ property.id }/delete" class="btn btn-mini btn-danger js-delete" data-prop-id="${ property.id }">Eliminar</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<hr/>
<a href="${ basePath }/property/new" class="btn btn-primary">Crear nueva propiedad</a>
