<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('.carousel').carousel('next', {
			interval : 4000

		});

	});
</script>

<br />

<div class="hero-unit" style="text-align: center;">
	<h1>ChinuProp</h1>
	<br />
	<p>Venta y Alquiler de Propiedades en Argentina. Su proxima casa
		puede ser oriental.</p>
	<p>
		<a class="btn btn-primary btn-large" href='${ basePath }/property/search'> Buscar
			propiedades </a>
	</p>
</div>

<hr />
<div class="row">
	<div class="span5 offset1">
		<h2>Propiedades en Alquiler</h2>
		<h5>
			<a href="${ basePath }/property/search">Ver todas</a>
		</h5>
		<p>
		<ul class="thumbnails home-thumbnails">
			<c:forEach var="prop" items="${rentProperties}" varStatus="i">
				<li class="span2"><a class="thumbnail"
					href='${ basePath }/property/view?id=${prop.id}'> <c:if
							test="${not empty prop.photos}">
							<c:forEach var="photo" items="${prop.photos}" varStatus="status">
								<c:if test="${status.first}">
									<img height="120" width="160"
										src="${ basePath }/photos?ID=${ photo.id }" alt="house" />
								</c:if>
							</c:forEach>
						</c:if> <c:if test="${empty prop.photos}">
							<img height="120" width="160"
								src="${ assetPath }/img/no-picture.jpg" alt="house" />
						</c:if>
						<h6>${ prop.neighborhood } - $${ prop.price }</h6>
				</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="offset1 span5">
		<h2>Propiedades en Venta</h2>
		<h5>
			<a href="${ basePath }/property/search">Ver todas</a>
		</h5>
		<p>
		<ul class="thumbnails home-thumbnails">
			<c:forEach var="prop" items="${sellProperties}" varStatus="i">
				<li class="span2"><a class="thumbnail"
					href='${ basePath }/property/view?id=${prop.id}'> <c:if
							test="${not empty prop.photos}">
							<c:forEach var="photo" items="${prop.photos}" varStatus="status">
								<c:if test="${status.first}">
									<img height="120" width="160"
										src="${ basePath }/photos?ID=${ photo.id }" alt="house" />
								</c:if>
							</c:forEach>
						</c:if> <c:if test="${empty prop.photos}">
							<img height="120" width="160"
								src="${ basePath }/assets/img/no-picture.jpg" alt="house" />
						</c:if>
						<h6>${ prop.neighborhood } - $${ prop.price }</h6>
				</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
