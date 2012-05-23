<%@ page isELIgnored="false"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<form:form name="property_newphoto" class="form-horizontal span8 offset2"
		action="new" method="POST" commandName="propertyphotoform" enctype="multipart/form-data">
		<form:errors path="*" />
		<fieldset>
			<legend>Agregar Nueva Foto a la Propiedad</legend>
			<input type="hidden" class="input-xlarge" id="freeArea"
				name="property" value="${ property.id }" /> 
			<div class="control-group">
				<label class="control-label" for="property_freeArea">Foto </label>

				<div class="controls">
					<input type="file" path="file" class="input-xlarge" id="photo"
						name="file" />
				</div>
			</div>

			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Agregar Foto" />
			</div>
		</fieldset>
	</form:form>
</div>