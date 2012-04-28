<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<div class="row">
	<form name="property_newproperty" class="form-horizontal span8 offset2"
		action="new" method="POST">
		<fieldset>
			<legend>Agregar Nueva Propiedad</legend>
			<jsp:include page="form.jsp"></jsp:include>
			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Agregar" />
				<button class="btn">Limpiar</button>
			</div>
		</fieldset>
	</form>
</div>