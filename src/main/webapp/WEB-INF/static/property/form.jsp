<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

			<div class="control-group">  
				<spring:bind path="propertyForm.owner">
					<input type="hidden" name="owner" value="${current_user.id}"/>
				</spring:bind>
			    <label class="control-label" for="property_type">Seleccione un tipo de inmueble </label>  
			    <div class="controls">  
			    	<form:select id="type" name="property_type" path="type">  
			    		<c:if test="${ propertyForm.type eq 'APARTMENT' }">
			    			<form:option value="APARTMENT" selected="selected">Departamento</form:option> 
			    		</c:if>
			    		<c:if test="${ propertyForm.type != 'APARTMENT' }">
			    			<form:option value="APARTMENT" >Departamento</form:option> 
			    		</c:if>
			    	
			    		<c:if test="${ propertyForm.type eq 'HOUSE' }">
			    			<form:option value="HOUSE" selected="selected">Casa</form:option> 
			    		</c:if>
			    		<c:if test="${ propertyForm.type != 'HOUSE' }">
				    		<form:option value="HOUSE" >Casa</form:option>  
					</c:if>
					</form:select>  
				</div>  
			</div>
			<div class="control-group">  
			    <label class="control-label" for="property_operation">Seleccione un tipo de operacion </label>  
			   	<div class="controls">  
			       	<form:select id="operation" name="property_operation" path="operation">  
			       		<c:if test="${ propertyForm.operation eq 'RENT' }">
			       			<form:option value="RENT" selected="selected">Alquiler</form:option>
			       		</c:if>
			       		<c:if test="${ propertyForm.operation != 'RENT' }">
			       			<form:option value="RENT" >Alquiler</form:option>
			       		</c:if>
			       		<c:if test="${ propertyForm.operation eq 'SELL' }">
			       			<form:option value="SELL" selected="selected">Venta</form:option> 
			       		</c:if>
			       		<c:if test="${ propertyForm.operation != 'SELL' }">
			       			<form:option value="SELL" >Venta</form:option> 
			       		</c:if>
			       		 
					</form:select>  
				</div>  
			</div>
			<div class="control-group">
				<label class="control-label" for="property_address">Direccion </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="address" path="address" name="property_address" value=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_neighborhood">Barrio </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="neighborhood" path="neighborhood" name="property_neighborhood" value=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_price">Precio </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="price" path="price" name="property_price" value=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_spaces">Cantidad de ambientes </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="spaces" path="spaces" name="property_spaces" value=""/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_coveredArea">Superficie cubierta </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="coveredArea" path="coveredArea" name="property_coveredArea" value=""/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_freeArea">Superficie descubierta </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="freeArea" path="freeArea" name="property_freeArea" value=""/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_age">Antiguedad </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="age" path="age" name="property_age" value=""/>
					<p>A�os</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_description">Descripcion </label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="description" path="description" name="property_description" value=""/>
				</div>
			</div>
			
			<div class="control-group">  
			  <label class="control-label" for="property_service">Seleccione los servicios disponibles </label>  
			  <div class="controls">
                <form:checkboxes path="services" items="${propertyServices}" />
			  </div>  
			</div>		
			