<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

			
			<div class="control-group">  
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
					<p>Años</p>
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
			    <label class="checkbox">  
					
				 <c:if test="${ propertyForm.service.cable }">
					 <form:input type="checkbox" id="service" path="service.cable" checked="checked" name="property_cable" />
			     </c:if>
			      <c:if test="${ ! propertyForm.service.cable }">
					 <form:input type="checkbox" id="service" path="service.cable" name="property_cable" />
			     </c:if>
			      Cable
			    </label>
			    <label class="checkbox">  
			     <c:if test="${ propertyForm.service.telephone }">
					<form:input type="checkbox" checked="checked" path="service.telephone" name="property_telephone" />
			     </c:if>
			     <c:if test="${ ! propertyForm.service.telephone }">
					<form:input type="checkbox"  path="service.telephone" name="property_telephone" />
			     </c:if>
			      Telefono
			    </label>
			    <label class="checkbox">  
			    <c:if test="${ propertyForm.service.swimmingpool }">
					<form:input type="checkbox" path="service.swimmingpool" checked="checked"  name="property_swimmingpool" />
			    </c:if>
			    <c:if test="${ ! propertyForm.service.swimmingpool }">
					<form:input type="checkbox" path="service.swimmingpool" name="property_swimmingpool" />
			    </c:if>
			      Pileta
			    </label>
			    <label class="checkbox">  
			    <c:if test="${ propertyForm.service.lobby }">
					<form:input type="checkbox" path="service.lobby" checked="checked" name="property_lobby" />
			    </c:if>
			    <c:if test="${ !propertyForm.service.lobby }">
					<form:input type="checkbox" path="service.lobby" name="property_lobby" />
			    </c:if>
			      Salon
			    </label>
			    <label class="checkbox">  
			    <c:if test="${ propertyForm.service.paddle }">
					<form:input type="checkbox" path="service.paddle"  checked="checked" name="property_paddle" />
			    </c:if>
			    <c:if test="${ !propertyForm.service.paddle }">
					<form:input type="checkbox" path="service.paddle" name="property_paddle" />
			    </c:if>
			      Cancha de paddle
			    </label>
			    <label class="checkbox">  
			    <c:if test="${ propertyForm.service.quincho }">
					<form:input type="checkbox" path="service.quincho"  checked="checked" name="property_quincho" />
			    </c:if>
			     <c:if test="${ !propertyForm.service.quincho }">
					<form:input type="checkbox" path="service.quincho" name="property_quincho" />
			    </c:if>
			        
			      Quincho
			    </label>
			  </div>  
			</div>		
			