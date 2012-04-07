<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row-fluid">
	<div class="span12">
		<div class="span8">
			<!--<ul class="breadcrumb">
				<li><a href="#">Inmuebles</a> <span class="divider">/</span></li>
				<li><a href="#">Compra</a> <span class="divider">/</span></li>
				<li class="active">Zona Oeste</li>
			</ul>-->
			<h1><c:out value=" ${property.address}" /></h1>
			<h2><c:out value=" ${property.neighborhood}" /> </h2>
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
						<dd>$<c:out value=" ${property.price}" /> </dd>
						
					</dl>

				</div>
				<li class="span5"><a href="#" class="thumbnail"> <img height="360" width="268" src="http://www.highgrowthpropertyinvestment.co.uk/media/Sell-Property.jpg" alt="house" />
				</a></li>
			</ul>
			<div class="span5"  style="margin: 0px;">
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
	<div class="span2" >
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Servicios</th>
				</tr>
			</thead>
			<tbody>
				<c:if test = "${property.service.telephone == 'true'}">   
					<tr>
						<td>Telefono</td>
					</tr>
				</c:if>
				<c:if test = "${property.service.swimmingpool == 'true'}">   
					<tr>
						<td>Pileta</td>
					</tr>
				</c:if>
				<c:if test = "${property.service.paddle == 'true'}">   
					<tr>
						<td>Paddle</td>
					</tr>
				</c:if>
				<c:if test = "${property.service.lobby == 'true'}">   
					<tr>
						<td>Lobby</td>
					</tr>
				</c:if>
				<c:if test = "${property.service.cable == 'true'}">   
					<tr>
						<td>Cable</td>
					</tr>
				</c:if>
				<c:if test = "${property.service.quincho == 'true'}">   
					<tr>
						<td>Quincho</td>
					</tr>
				</c:if>
				
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
			
		</div>
		<div class="span3">
	<script type="text/javascript">
	 $(document).ready(function () {
  		$(".js-contact-button").on("click", function() {
  			$(this).parent().hide();
  		});
  	});
  	</script>
		<form name="contactrequest" class="form-vertical" action="/contactrequest" method="POST">
		<fieldset>
			<legend>Contacta al Publicador</legend>
			<div id="hiddenform" class="collapse" >
			<div class="control-group">
				<label class="control-label" for="first_name">Nombre</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="first_name" name="first_name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="last_name">Apellido</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="last_name" name="last_name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="email">Email</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="telephone" name="email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Teléfono</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="phone" name="phone">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="phone">Descripcion</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="description">
				</div>
			</div>
			
			<button type="submit" class="btn btn-primary">Obtener
						datos del Publicador</button>
			
			</div>
		</fieldset>
	
			</form>
			<div class="form-actions" style="width:230px;">
            <button class="btn js-contact-button"  data-toggle="collapse" data-target="#hiddenform">Mostrar Formulario</button>
         	</div>
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