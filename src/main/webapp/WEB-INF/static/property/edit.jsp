<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<form:form name="property_editproperty" class="form-horizontal span8 offset2"
		action="edit" method="POST" commandName="propertyForm">
		<fieldset>
			<legend>Editar Propiedad</legend>
			<spring:bind path="propertyForm.property">
				<input type="hidden" name="property" value="${propertyForm.property.id}"/>
			</spring:bind>
			<jsp:include page="form.jsp"></jsp:include>
			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Editar" />
				<button class="btn">Limpiar</button>
			</div>
		</fieldset>
	</form:form>
</div>