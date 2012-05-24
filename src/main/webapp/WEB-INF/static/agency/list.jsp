<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h3>Listado de Inmobiliarias</h3>


<c:if test="${ empty agencyList }">
	<tr>
		<td>
			<h3>No hay inmobiliarias con publicaciones activas disponibles.</h3>
		</td>
	</tr>
</c:if>

<c:if test="${ not empty agencyList }">
	<table class="table table-striped">
		<thead>
			<tr>
				<td></td>
				<td>Nombre</td>
				<td>Avisos publicados</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="agency" items="${ agencyList }">
				<tr>
					<td><img height="100" width="100"
						src="${ basePath }/photo/view?ID=${ agency.photoID }"
						alt="property" /></td>
					<td>${ agency.agencyName }</td>
					<td>${ agency.propCount }</td>
					<td><form:form action="${ basePath }/property/search"
							commandName="searchForm" method="POST">
							<input type="hidden" name="user" id="user" value="${ agency.id }" />
							<input type="submit" class="btn btn-primary"
								value="Ver todas las publicaciones" />
						</form:form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>