<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="subnav subnav-fixed">


	<form:form class="navbar-form pull-center" method="post"
		action="search" commandName="searchForm">

		<fieldset>
			<ul class="nav nav-pills">
				<li><h6>Operación:</h6></li>
				<li><form:select id="operation" name="operation"
						path="operation" style="width: 100px">
						<form:option value="All">Todos</form:option>
						<c:if test="${searchForm.operation == 'SELL'}">
							<form:option value="SELL" selected="selected">Venta</form:option>
						</c:if>
						<c:if test="${searchForm.operation != 'SELL'}">
							<form:option value="SELL">Venta</form:option>
						</c:if>
						<c:if test="${searchForm.operation == 'RENT'}">
							<form:option value="RENT" selected="selected">Alquiler</form:option>
						</c:if>
						<c:if test="${searchForm.operation != 'RENT'}">
							<form:option value="RENT">Alquiler</form:option>
						</c:if>
					</form:select></li>
				<li class="divider"><hr /></li>
				<li><h6>Tipo:</h6></li>
				<li><form:select id="type" name="type" path="type"
						style="width: 100px">
						<form:option value="All">Todos</form:option>
						<c:if test="${searchForm.type == 'HOUSE'}">
							<form:option value="HOUSE" selected="selected">Casa</form:option>
						</c:if>
						<c:if test="${searchForm.type != 'HOUSE'}">
							<form:option value="HOUSE">Casa</form:option>
						</c:if>
						<c:if test="${searchForm.type == 'APARTMENT'}">
							<form:option value="APARTMENT" selected="selected">Departamento</form:option>
						</c:if>
						<c:if test="${searchForm.type != 'APARTMENT'}">
							<form:option value="APARTMENT">Departamento</form:option>
						</c:if>
					</form:select></li>
				<li class="divider"></li>
				<li><h6>Orden:</h6></li>
				<li><form:select id="order" name="order" path="order"
						style="width: 100px">
						<c:if test="${searchForm.order == 'ASC'}">
							<form:option value="Asc" selected="selected">Ascendente</form:option>
						</c:if>
						<c:if test="${searchForm.order != 'ASC'}">
							<form:option value="Asc">Ascendente</form:option>
						</c:if>
						<c:if test="${searchForm.order == 'DESC'}">
							<form:option value="Desc" selected="selected">Descendiente</form:option>
						</c:if>
						<c:if test="${searchForm.order != 'DESC'}">
							<form:option value="Desc">Descendiente</form:option>
						</c:if>
					</form:select></li>
				<li class="divider"></li>
				<li><input type="button" class="btn btn-info"
					id="js-advanced-search" value="Más opciones" /></li>
				<li class="divider"></li>
				<li><form:input type="hidden" value="0" name="page" path="page" />
					<input type="submit" class="btn btn-primary" value="Buscar" /></li>
			</ul>
			<ul class="nav nav-pills hidden js-secondary-navbar">
				<li><h6>Precio:</h6></li>
				<li><input type="text" name="pricelow" class="span2"
					value="${ searchForm.pricelow == -1 ? '' : searchForm.pricelow }"
					placeholder="Desde"></li>
				<li class="divider"></li>
				<li><input type="text" name="pricehigh" class="span2"
					value="${ searchForm.pricehigh == -1 ? '' : searchForm.pricehigh}"
					placeholder="Hasta" /></li>
				<li class="divider"></li>
				<li><h6>Inmobiliaria:</h6></li>
				<li><form:select path="user"
						style="width: 100px">
						<form:option value="any">Cualquiera</form:option>
						<c:forEach var="agency" items="${ realStateAgencies }">
							<form:option value="${ agency.id }">${ agency.agencyName }</form:option>
						</c:forEach>
					</form:select></li>
				<li class="divider"></li>
				<li>
					<div class="btn-group servicesDrop">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							Servicios <span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><form:checkboxes path="services"
									items="${propertyServices}" delimiter="</li><li>" /></li>
						</ul>
					</div>
				</li>
				<li class="divider"></li>
				<li>
					<div class="btn-group ambientsDrop">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							Ambientes <span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<c:forEach items="${ roomTypes }" var="roomType" varStatus="i">
								<li>
									<spring:bind path="searchForm.rooms[${i.index}].type">
										<input type="checkbox" id="rooms[${i.index }].type" value="${ roomType }" name="rooms[${i.index}].type">
									</spring:bind>
									<label for="rooms[${i.index}]">${ roomType.humanName }</label>
								</li>
								<li>
									<spring:bind path="searchForm.rooms[${i.index}].minSize">
										<input type="text" id="rooms[${i.index }].minSize" class="span1 pull-left" name="rooms[${i.index}].minSize" placeholder="Desde m&#178;"/>
									</spring:bind>
									<spring:bind path="searchForm.rooms[${i.index}].maxSize"> 
										<input type="text" id="rooms[${i.index }].maxSize" class="span1 pull-left" name="rooms[${i.index}].maxSize" placeholder="Hasta m&#178;"/>
									</spring:bind>
								</li>
								<hr/>
							</c:forEach>
						</ul>
					</div>
				</li>
			</ul>
		</fieldset>
		<form:errors path="*" />
	</form:form>

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
					<a href='${ basePath }/property/view?id=${prop.id}'> ${
						prop.address } - ${ prop.neighborhood } </a>
				</h3>
				<div class="row">
					<div class="span2 pull-left">
						<a class="thumbnail"
							href='${ basePath }/property/view?id=${prop.id}'> <c:if
								test="${not empty prop.photos}">
								<c:forEach var="photo" items="${prop.photos}" varStatus="status">
									<c:if test="${status.first}">
										<img height="120" width="160"
											src="${ basePath }/photo/view?ID=${ photo.id }" alt="house" />
									</c:if>
								</c:forEach>
							</c:if> <c:if test="${empty prop.photos}">
								<img height="120" width="160"
									src="${ basePath }/assets/img/no-picture.jpg" alt="house" />
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
						<br /> <b>Dirección:</b> ${ prop.address } <br /> <b>Barrio:</b>
						${ prop.neighborhood } <br />
					</div>
					<div class="span2 pull-right">
						<p style="color: #2F5D9F; font-size: 30px;">
							$
							<c:out value=" ${prop.price}" />
						<p>
							<br /> <a class="btn btn-large"
								href="${ basePath }/property/view?id=${prop.id}">Ver detalle</a>
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
