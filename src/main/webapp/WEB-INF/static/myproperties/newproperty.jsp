<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="row">
	<form name="property_newproperty" class="form-horizontal span8 offset2" action="new" method="POST">
	<fieldset>
			<legend>Agregar Nueva Propiedad</legend>
			<div class="control-group">  
           		<label class="control-label" for="property_type">Seleccione un tipo de inmueble </label>  
            		<div class="controls">  
            			<select id="type" name="property_type">  
               				<option value="APARTMENT">Departamento</option>  
               				<option value="HOUSE">Casa</option>  
						</select>  
					</div>  
			</div>
			<div class="control-group">  
           		<label class="control-label" for="property_operation">Seleccione un tipo de operacion </label>  
            		<div class="controls">  
            			<select id="operation" name="property_operation">  
               				<option value="RENT">Alquiler</option>  
               				<option value="SELL">Venta</option>  
						</select>  
					</div>  
			</div>
			<div class="control-group">
				<label class="control-label" for="property_address">Direccion </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="address" name="property_address"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_neighborhood">Barrio </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="neighborhood" name="property_neighborhood"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_price">Precio </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="price" name="property_price"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_spaces">Cantidad de ambientes </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="spaces" name="property_spaces"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_coveredArea">Superficie cubierta </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="coveredArea" name="property_coveredArea"/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_freeArea">Superficie descubierta </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="freeArea" name="property_freeArea"/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_age">Antiguedad </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="age" name="property_age"/>
					<p>Años</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="property_description">Descripcion </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="property_description">
				</div>
			</div>
			<div class="control-group">  
            <label class="control-label" for="property_service">Seleccione los servicios disponibles </label>  
            <div class="controls">  
              <label class="checkbox">  
                <input type="checkbox" id="service" value="cable" name="property_cable">  
                Cable
              </label>
              <label class="checkbox">  
                <input type="checkbox" value="telephone" name="property_telephone">  
                Telefono
              </label>
              <label class="checkbox">  
                <input type="checkbox" value="swimmingpool"  name="property_swimmingpool">  
                Pileta
              </label>
              <label class="checkbox">  
                <input type="checkbox" value="lobby"  name="property_lobby">  
                Salon
              </label>
              <label class="checkbox">  
                <input type="checkbox" value="paddle"  name="property_paddle">  
                Cancha de paddle
              </label>
              <label class="checkbox">  
                <input type="checkbox" value="quincho"  name="property_quincho">  
                Quincho
              </label>
            </div>  
          </div>  
			<div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Agregar"/>
            <button class="btn">Limpiar</button>
          </div>
		</fieldset>
	</form>
</div>