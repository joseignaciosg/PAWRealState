<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subnav subnav-fixed">
	<form class="navbar-form pull-center" method="post" action="search">
		<fieldset>
			<ul class="nav nav-pills">
				<li><input type="text" name="pricelow" class="span2"
					value="${ pricelow == -1 ? '' : pricelow }"
					placeholder="Precio desde"></li>
				<li class="divider-vertical"></li>
				<li><input type="text" name="pricehigh" class="span2"
					value="${ pricehigh == -1 ? '' : pricehigh}"
					placeholder="Precio hasta" /></li>
				<li class="divider-vertical"></li>
				<li><h6>Operación:</h6></li>
				<li><select id="operation" name="operation"
					style="width: 100px">
						<option value="All">Todos</option>
						<c:if test="${operation == 'Sell'}">
							<option value="Sell" selected="selected">Venta</option>
						</c:if>
						<c:if test="${operation != 'Sell'}">
							<option value="Sell">Venta</option>
						</c:if>
						<c:if test="${operation == 'Rent'}">
							<option value="Rent" selected="selected">Alquiler</option>
						</c:if>
						<c:if test="${operation != 'Rent'}">
							<option value="Rent">Alquiler</option>
						</c:if>
				</select></li>
				<li class="divider-vertical"><hr /></li>
				<li><h6>Tipo:</h6></li>
				<li><select id="type" name="type" style="width: 100px">
						<option value="All">Todos</option>
						<c:if test="${type == 'House'}">
							<option value="House" selected="selected">Casa</option>
						</c:if>
						<c:if test="${type != 'House'}">
							<option value="House">Casa</option>
						</c:if>
						<c:if test="${type == 'Apartment'}">
							<option value="Apartment" selected="selected">Departamento</option>
						</c:if>
						<c:if test="${type != 'Apartment'}">
							<option value="Apartment">Departamento</option>
						</c:if>
				</select></li>
				<li class="divider-vertical"></li>
				<li><h6>Orden:</h6></li>
				<li><select id="order" name="order" style="width: 100px">
						<c:if test="${order == 'Asc'}">
							<option value="Asc" selected="selected">Ascendente</option>
						</c:if>
						<c:if test="${order != 'Asc'}">
							<option value="Asc">Ascendente</option>
						</c:if>
						<c:if test="${order == 'Desc'}">
							<option value="Desc" selected="selected">Descendiente</option>
						</c:if>
						<c:if test="${order != 'Desc'}">
							<option value="Desc">Descendiente</option>
						</c:if>
				</select></li>
				<li class="divider-vertical"></li>
				<li><input type="hidden" value="0" name="page" /> <input
					type="submit" class="btn btn-primary" value="Buscar"></li>
			</ul>
		</fieldset>
	</form>

</div>

<c:if test="${ empty props }">
	<br />
	<h3 class="search-result">No hay resultados para la búsqueda
		realizada.</h3>
</c:if>
<c:if test="${! empty props }">
	<br />
	<br />
	<c:forEach var="prop" items="${props}" varStatus="i">
		<div class="row">
			<div class="offset2 span8">
				<h3 class="property-title">
						<a href='${ basePath }/properties/view?id=${prop.id}'> ${
							prop.address } - ${ prop.neighborhood } </a>
				</h3>
				<div class="row">
					<div class="span2 pull-left">
						<a class="thumbnail"
							href='${ basePath }/properties/view?id=${prop.id}'> <c:if
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
						</a>
					</div>
					<div class="span3">
						<b>Operación:</b>
						<c:if test="${prop.operation == 'SELL'}">   
								En Venta
							</c:if>
						<c:if test="${prop.operation == 'RENT'}">   
								En Alquiler
							</c:if>
						<br /> <b>Tipo de Propiedad:</b>
						<c:if test="${prop.type == 'HOUSE'}">   
								Casa
							</c:if>
						<c:if test="${prop.type == 'APARTMENT'}">   
								Departamento
							</c:if>
						<br /> <b>Dirección:</b> ${ prop.address } <br /> <b>Barrio:</b> ${
						prop.neighborhood } <br />
					</div>
					<div class="span2 pull-right">
						<p style="color: #2F5D9F; font-size: 30px;">
							$
							<c:out value=" ${prop.price}" />
						<p>
							<br /> <a class="btn btn-large"
								href="${ basePath }/properties/view?id=${prop.id}">Ver detalle</a>
				</div>
				</div>
				<div class="clear"></div>
			</div>
			<hr class="offset2 span8" />
		</div>
		

	</c:forEach>
</c:if>

<div class="pagination subnavbottom">
	<ul>
		<c:if test="${pagenum-1 >= 0}">
			<li><a
				href='search?page=<c:out value="${pagenum-1}"/>&operation=<c:out value="${operation}"/>&type=<c:out value="${type}"/>&pricelow=<c:out value="${pricelow}"/>&pricehigh=<c:out value="${pricehigh}"/>&order=<c:out value="${order}"/>'>Anterior</a></li>
		</c:if>
		<c:if test="${! empty props  }">
			<li><a
				href='search?page=<c:out value="${pagenum+1}"/>&operation=<c:out value="${operation}"/>&type=<c:out value="${type}"/>&pricelow=<c:out value="${pricelow}"/>&pricehigh=<c:out value="${pricehigh}"/>&order=<c:out value="${order}"/>'>Siguiente</a></li>
		</c:if>
	</ul>
</div>
