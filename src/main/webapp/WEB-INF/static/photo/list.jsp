<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="offset3 span6">
	<h6>
		<a href="${ basePath }/property/view?id=${ property.id }">Fotos
			de ${ property.address } - ${ property.neighborhood }</a>
	</h6>

	<hr />
	<p>
		<c:if test="${ not empty property.photos }">
			<ul class="thumbnails">
				<c:forEach var="photo" items="${ property.photos }">
					<li class="span2"><a href="#" class="thumbnail"> <img
							height="120" width="160"
							src="${ basePath }/photo/show?ID=${ photo.id }" alt=""/>
					</a>
					<form method="POST" action="${ basePath }/photo/delete" class="centered">
						<input type="hidden" name="photoId" value="${ photo.id }"/>
						<input type="submit" class="btn btn-danger" value="Eliminar"/>
					</form></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${ empty property.photos }">
			La propiedad no tiene fotos
		</c:if>
	</p>
	<div class="centered">
		<a href="${ basePath }/photo/new?propertyId=${ property.id }" class="btn btn-primary btn-large">Agregar foto</a>
	</div>
</div>