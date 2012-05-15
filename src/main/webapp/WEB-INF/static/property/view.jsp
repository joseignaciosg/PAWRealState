<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	 $(document).ready(function () {
  		$('.carousel').carousel('next',{
  				interval: 4000
  			
  		});
  	 });
</script>
  
<div class="row-fluid" >
	<div class="span12">
		<div class="span8">
			
			
			<h1><c:out value=" ${property.address}" /></h1>
			<h2><c:out value=" ${property.neighborhood}" /> </h2>
			<h4>Cantidad de Visitas: <c:out value=" ${property.visitCount}" /> </h4>
			
		
			<br />
			<ul class="thumbnails">			
				<div class="span3">
					<dl>
						<dt><h4>Tipo de Inmueble</h4></dt>
						<c:if test = "${property.type == 'HOUSE'}">   
							<dd>Casa</dd>
						</c:if>
						<c:if test = "${property.type == 'APARTMENT'}">   
							<dd>Departamento</dd>
						</c:if>
						<dt><h4>Direccion</h4></dt>
						<dd><c:out value=" ${property.address}" /> </dd>
						<dt><h4>Estado</h4></dt>
						<c:if test = "${property.operation == 'SELL'}">   
							<dd>En Venta</dd>
						</c:if>
						<c:if test = "${property.operation == 'RENT'}">   
							<dd>En Alquiler</dd>
						</c:if>
						<dt><h4>Precio</h4></dt>
						<dd>$<c:out value="${property.price}" /> </dd>			
					
					<c:if test="${property.owner.class.name == 'ar.edu.itba.it.paw.domain.entities.RealStateAgency'}">
						<dt><h4>Agencia</h4></dt>
						<dd><c:out value=" ${property.owner.agencyName}" /> </dd>
						<c:if test="${property.owner.photo != null}">
							<img height="100" width="100" src="${ basePath }/photo/view?ID=${ property.owner.photo.id }" alt=""  />
						</c:if>
						<c:if test='${property.owner.photo == null}'>
							<img height="100" width="100" src="${ assetPath }/img/realstate-no-picture.jpg" alt="" />
						</c:if>					
						
					</c:if>
					</dl>
				</div>
				
				
				<div class="span7"  >
					<div id="myCarousel" class="carousel" style="overflow:hidden; height:260px;">
						<c:if test="${property.reserved}">				
							<img src="${ assetPath }/img/reservado_background.png" height="100" width="100" style="position: absolute; top: 0px; left: 0px;z-index: 100;"/>
						</c:if>	
	 				 <!-- Carousel items -->
	  					<div class="carousel-inner">
							<c:choose>
								<c:when test="${ not empty property.photos }">
									<c:forEach var="photo" items="${property.photos}">
										<div class="item">
												<div>
													<img height="100" width="425" src="${ basePath }/photo/view?ID=${ photo.id }" alt="" style="opacity=0.4;" />
												</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="item">
										<img height="260" width="425"
											src="${ assetPath }/img/no-picture.jpg" alt="" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					
				 	<!-- Carousel nav -->
				 		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
				  		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
				
				</div>
				
			</ul>
			<div class="span6"  style="margin: 0px;">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Caracteristicas Generales</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Cantidad de Ambientes: <c:out value=" ${property.spaces}" /></td>
				</tr>
				<tr>
					<td>Area cubierta: <c:out value=" ${property.coveredArea}" /></td>
				</tr>
				<tr>
					<td>Area Libre: <c:out value=" ${property.freeArea}" /></td>
				</tr>
				<tr>
					<td>Antiguedad: <c:out value=" ${property.age}"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="span5" >
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Servicios</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${property.services}" var="service">
						<tr>
							<td><c:out value="${service}"/></td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="span8"  style="margin: 0px;">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Descripción</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value=" ${property.description}" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="span8"  style="margin: 0px;">
		<!--<a class="btn" href="${ basePath }/property/search?user=${ property.owner.id }">Más propiedades del propietario de esta propiedad »</a-->
		<a href="${ basePath }/property/search?user=${ property.owner.id }" class="btn btn-primary btn-small">Más propiedades del propietario de esta propiedad »</a>
	</div>		
	
		</div>
		<div class="span3">
	<script type="text/javascript">
	
	 $(document).ready(function () {
  		$(".js-contact-button").on("click", function() {
  			$(this).parent().hide();
  		});
  	 });
  	</script>
		<form:form class="form-vertical" action="${ basePath }/property/contactrequest" method="POST" commandName="contactRequestForm">
		<fieldset>
			<legend>Contacta al Publicador</legend>
			<form:input type="hidden" class="input-xlarge" id="propertyId" name="propertyId" value="${ property.id }" path="property"/>
			<div id="hiddenform" class="collapse" ></div>
			
			<div class="control-group">
				<form:label class="control-label" path="firstName">Nombre</form:label>
				<div class="controls">
					<form:input class="input-xlarge" id="firstName" name="firstName" path="firstName"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="lastName">Apellido</label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="lastName" name="lastName" path="lastName"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">Email</label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="email" name="email" path="email"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Teléfono</label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="phone" name="phone" path="phone"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Descripcion</label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="description" name="description" path="description"/>
				</div>
			</div>
			
			<button type="submit" class="btn btn-primary">Obtener
						datos del Publicador</button>
			
		
		</fieldset>
	</form:form>
			<!--<div class="form-actions" style="width:230px;">
            <button class="btn js-contact-button"  data-toggle="collapse" data-target="#hiddenform">Mostrar Formulario</button>
         	</div>-->
				<table class="table table-striped">
					<thead>
					    <tr>
					      <th>Ubicacion Geografica</th>  
					    </tr>
					  </thead>
					<tbody>
						<tr>
					    <td>
								<script type="text/javascript">
	  $(document).ready(function () {
		options = 
		{ markers: [
		                            { address: "<c:out value=" ${property.address}" />, <c:out value=" ${property.neighborhood}" />, Buenos Aires, Argentina",
		                              html: "<c:out value=" ${property.address}" /> - <c:out value=" ${property.neighborhood}" /> <br/> Buenos Aires, Argentina" },],
		                  address: "<c:out value=" ${property.address}" />, <c:out value=" ${property.neighborhood}" />, Buenos Aires, Argentina",
		                  zoom: 15 }
	  	$("#map").gMap(options);
	});
	  </script>
							<div id="map"  style="height:380px; width:260px;"></div>	
						</td>
						</tr>
					  </tbody>
				</table>
		</div>
	</div>

	
</div>
</div>