<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<form name="property_newproperty" class="form-horizontal span8 offset2"
		action="edit?ID=${ property.id }" method="POST">
		<fieldset>
			<legend>Editar Propiedad</legend>
			<jsp:include page="form.jsp"></jsp:include>
			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Editar" />
				<button class="btn">Limpiar</button>
			</div>
		</fieldset>
	</form>
</div>