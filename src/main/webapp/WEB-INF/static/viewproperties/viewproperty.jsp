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
			<h2><c:out value=" ${property.address}" /></h2>
			<h3><c:out value=" ${property.neighborhood}" /> </h3>
			<br />
			<ul class="thumbnails">
				<li class="span4"><a href="#" class="thumbnail"> <img height="360" width="268" src="http://www.highgrowthpropertyinvestment.co.uk/media/Sell-Property.jpg" alt="house" />
				</a></li>

				<div class="span4">
					<dl>
						<dt>Tipo de Inmueble</dt>
						<c:if test = "${property.type == 'HOUSE'}">   
							<dd>Casa</dd>
						</c:if>
						<c:if test = "${property.type == 'APARTMENT'}">   
							<dd>Departamento</dd>
						</c:if>
						<dt>Direccion</dt>
						<dd><c:out value=" ${property.address}" /> </dd>
						<dt>Estado</dt>
						<c:if test = "${property.operation == 'SELL'}">   
							<dd>En Venta</dd>
						</c:if>
						<c:if test = "${property.operation == 'RENT'}">   
							<dd>En Alquiler</dd>
						</c:if>
						<dt>Precio</dt>
						<dd>$<c:out value=" ${property.price}" /> </dd>
						
					</dl>

				</div>
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

			<form class="form-vertical">
				<fieldset>
					<legend>Contacta al Publicador</legend>
					<label>Tu Nombre</label> 
					<input type="text" class="span3" placeholder="Requerido…"> 
					<label>Tu Apellido</label> 
					<input type="text" class="span3" placeholder="Requerido…"> 
					<label>Tu e-mail de contacto</label> 
					<input type="text" class="span3" placeholder="Requerido…"> 
					<label>Tu Consulta</label> 
					<input type="text" class="span3" placeholder="Opcional…"><br />
					<button type="submit" class="btn btn-primary">Obtener
						datos del Publicador</button>
				</fieldset>
			</form>
		</div>
	</div>

	
</div>
</div>