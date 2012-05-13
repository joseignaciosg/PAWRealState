<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Listado de Inmobiliarias</h3>

<table class="table table-striped">
	<thead>
		<tr>
			<td>Logo</td>
			<td>Nombre</td>
			<td>Avisos publicados</td>
			<td>Hipervinculo</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="agency" items="${ agencyList }">
			<tr>
				<td>${ agency.photo }</td>
				<td>${ agency.agencyName }</td>
				<td>${ agency.published }</td>
				<td><a href="${ basePath }/propety/search">Ver todas las publicaciones</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>