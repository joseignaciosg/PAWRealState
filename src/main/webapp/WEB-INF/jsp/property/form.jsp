<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="control-group">  
    <label class="control-label" for="property_type">Seleccione un tipo de inmueble </label>  
    <div class="controls">  
    	<select id="type" name="property_type">  
    		<option value="APARTMENT" 
    			<c:if test="${ property.propertyType eq 'APARTMENT' }">
    			selected="selected"
    			</c:if>
    		>
    			Departamento</option>  
    		<option value="HOUSE"
    			<c:if test="${ property.propertyType eq 'HOUSE' }">
    			selected="selected"
    			</c:if>
    		>
    			Casa</option>  
		</select>  
	</div>  
</div>
<div class="control-group">  
    <label class="control-label" for="property_operation">Seleccione un tipo de operacion </label>  
   	<div class="controls">  
       	<select id="operation" name="property_operation">  
       		<option value="RENT"
       			<c:if test="${ property.operationType eq 'RENT' }">
    			selected="selected"
    			</c:if>
       		>
       			Alquiler</option>  
       		<option value="SELL"
       			<c:if test="${ property.operationType eq 'SELL' }">
    			selected="selected"
    			</c:if>
       		>
       		Venta</option>  
		</select>  
	</div>  
</div>
<div class="control-group">
	<label class="control-label" for="property_address">Direccion </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="address" name="property_address" value="${ property.address }"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_neighborhood">Barrio </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="neighborhood" name="property_neighborhood" value="${ property.neighborhood }"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_price">Precio </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="price" name="property_price" value="${ property.price }"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_spaces">Cantidad de ambientes </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="spaces" name="property_spaces" value="${ property.spaces }"/>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_coveredArea">Superficie cubierta </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="coveredArea" name="property_coveredArea" value="${ property.coveredArea }"/>
		<p>Metros cuadrados</p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_freeArea">Superficie descubierta </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="freeArea" name="property_freeArea" value="${ property.freeArea }"/>
		<p>Metros cuadrados</p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_age">Antiguedad </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="age" name="property_age" value="${ property.age }"/>
		<p>Años</p>
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="property_description">Descripcion </label>
	<div class="controls">
		<input type="text" class="input-xlarge" id="description" name="property_description" value="${ property.description }">
	</div>
</div>
<div class="control-group">  
  <label class="control-label" for="property_service">Seleccione los servicios disponibles </label>  
  <div class="controls">  
    <label class="checkbox">  
      <input type="checkbox" id="service" ${ property.service.cable ? 'checked="checked"' : '' } name="property_cable" >  
      Cable
    </label>
    <label class="checkbox">  
      <input type="checkbox" ${ property.service.telephone ? 'checked="checked"' : '' } name="property_telephone">  
      Telefono
    </label>
    <label class="checkbox">  
      <input type="checkbox" ${ property.service.swimmingpool ? 'checked="checked"' : '' }  name="property_swimmingpool">  
      Pileta
    </label>
    <label class="checkbox">  
      <input type="checkbox" ${ property.service.lobby ? 'checked="checked"' : '' } name="property_lobby">  
      Salon
    </label>
    <label class="checkbox">  
      <input type="checkbox" ${ property.service.paddle ? 'checked="checked"' : '' } name="property_paddle">  
      Cancha de paddle
    </label>
    <label class="checkbox">  
      <input type="checkbox" ${ property.service.quincho ? 'checked="checked"' : '' }  name="property_quincho">  
      Quincho
    </label>
  </div>  
</div>  