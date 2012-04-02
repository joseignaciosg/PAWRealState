<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row-fluid">

	<div class="span12">
		<div class="span9">
			<ul class="breadcrumb">
				<li><a href="#">Inmuebles</a> <span class="divider">/</span></li>
				<li><a href="#">Compra</a> <span class="divider">/</span></li>
				<li class="active">Zona Oeste</li>
			</ul>
			<h2><c:out value=" ${property.address}" /></h2>
			<h3><c:out value=" ${property.neighborhood}" /> </h3>
			<br />

			<ul class="thumbnails">
				<li class="span4"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/360x268" alt="">
				</a></li>

				<div class="span5">
					<dl>
						<dt>Tipo de Inmueble</dt>
						<dd><c:out value=" ${property.type}" /> </dd>
						<dt>Direccion</dt>
						<dd><c:out value=" ${property.address}" /> </dd>
						<dt>Superficie Total</dt>
						<dd><c:out value=" ${property.freeArea}" /> </dd>
						<dt>Superficie Cubierta</dt>
						<dd><c:out value=" ${property.coveredArea}" /> </dd>
					</dl>

				</div>
			</ul>
		</div>
		<div class="span3">

			<form class="form-vertical">
				<fieldset>
					<legend>Contacta al Publicador</legend>
					<label>Tu Nombre</label> <input type="text" class="span3"
						placeholder="Requerido…"> <label>Tu Apellido</label> <input
						type="text" class="span3" placeholder="Requerido…"> <label>Tu
						e-mail de contacto</label> <input type="text" class="span3"
						placeholder="Requerido…"> <label>Tu Consulta</label> <input
						type="text" class="span3" placeholder="Requerido…"><br />
					<button type="submit" class="btn btn-primary">Obtener
						datos del Publicador</button>
				</fieldset>
			</form>
		</div>
	</div>

	<div class="span6">
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
					<td>Luminosidad: Excelente</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="span3">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Servicos</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Luz</td>
				</tr>
				<tr>
					<td>Agua</td>
				</tr>
				<tr>
					<td>Gas</td>
				</tr>
				<tr>
					<td>TV-Cable</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="span9">
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
</div>