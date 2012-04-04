<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row-fluid">
	<div class="span12">
		<div class="span6">

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Datos del Publicador</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Nombre: <c:out value=" ${user.name}" /></td>
				</tr>
				<tr>
					<td>Apellido: <c:out value=" ${user.surname}" /></td>
				</tr>
				<tr>
					<td>E-Mail: <c:out value=" ${user.mail}" /></td>
				</tr>
				<tr>
					<td>Telefono: <c:out value=" ${usert.telephone}"/></td>
				</tr>
			</tbody>
		
		</table>
			<a class="btn btn-primary" href='${ basePath }/properties/view?id=<c:out value="${property.id}"/>'>Volver</a>
		</div>

	
</div>
</div>

