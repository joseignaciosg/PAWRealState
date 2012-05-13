<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="row">
	<form:form name="property_newproperty" class="form-horizontal span8 offset2"
		action="new" method="POST" commandName="propertyForm">
		<form:errors path="*"/>
		
		<fieldset>
			<legend>Agregar Nueva Propiedad</legend>
			<jsp:include page="form.jsp"></jsp:include>
			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Agregar" />
			</div>
		</fieldset>
	</form:form>
</div>