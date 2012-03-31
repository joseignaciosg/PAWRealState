<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="offset2 span8">
		<h3>Listado de Hoteles</h3>
		<hr />
	</div>
</div>

<div class="row">
	<div class="offset2 span8">
		<table class="table js-hotel-list">
			<thead>
				<tr>
					<td class="name-column">Nombre</td>
					<td>Acciones</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="hotel" items="${ hotels }">
					<tr class="js-hotel-list-item">
						<td>${ hotel.name }</td>
						<td>
							<div class="btn-group">
								<a href="/hotels/${ hotel.id }" class="btn">Ver</a> <a href="#"
									class="btn js-btn-edit" data-id="${ hotel.id }"
									data-name="${ hotel.name }"
									data-description="${ hotel.description }">Editar</a> <a
									href="#" class="btn js-btn-delete" data-id="${ hotel.id }">Eliminar</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-primary js-create-hotel" data-toggle="modal"
			href="#hotel-modal">Nuevo Hotel</a>
	</div>
</div>



<div class="fade modal hotel-modal hidden" id="hotel-modal">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">×</a>
		<h3>Nuevo Hotel</h3>
	</div>
	<div class="modal-body">
		<form action='hotels' method='POST' class="form form-horizontal">
			<fieldset>
				<div class="control-group">
					<label class="control-label">Nombre:</label>
					<div class="controls">
						<input type='text' name='hotel_name' class="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Descripción:</label>
					<div class="controls">
						<textarea rows="10" cols="14" name="hotel_description"
							class="input-xlarge"></textarea>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn js-cancel-modal">Cerrar</a> <a href="#"
			class="btn btn-primary js-submit-modal">Crear Hotel</a>
	</div>
</div>
