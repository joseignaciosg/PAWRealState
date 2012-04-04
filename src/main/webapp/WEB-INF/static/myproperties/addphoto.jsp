<%@ page isELIgnored="false"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="row">
	<form name="property_newphoto" class="form-horizontal span8 offset2"
		action="add" method="POST" enctype="multipart/form-data">
		<fieldset>
			<legend>Agregar Nueva Foto a la Propiedad</legend>
			<input type="hidden" class="input-xlarge" id="freeArea"
				name="propertyId" value="${ property.id }" />
			<div class="control-group">
				<label class="control-label" for="property_freeArea">Foto </label>
				<div class="controls">
					<input type="file" class="input-xlarge" id="freeArea"
						name="property_freeArea" />
				</div>
			</div>

			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Agregar Foto" />
				<button class="btn">Limpiar</button>
			</div>
		</fieldset>
	</form>
</div>