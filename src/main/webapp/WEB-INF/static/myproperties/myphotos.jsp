<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="offset3 span6">
	<h6>
		<a href="${ basePath }/properties/view?id=${ property.id }">Fotos
			de ${ property.address } - ${ property.neighborhood }</a>
	</h6>

	<hr />
	<p>
		<c:if test="${ empty property.photos }">
			<ul class="thumbnails">
				<c:forEach var="photo" items="${ property.photos }">
					<li class="span2"><a href="#" class="thumbnail"> <img
							height="120" width="160"
							src="${ basePath }/photos?ID=${ photo }" alt=""/>
					</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${ not empty property.photos }">
			La propiedad no tiene fotos
		</c:if>
	</p>
	<div style="text-align: center">
		<a href="${ basePath }/myproperties/newphoto?propertyId=${ property.id }" class="btn btn-primary btn-large">Agregar foto</a>
	</div>
</div>