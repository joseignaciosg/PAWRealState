<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="row">
	<form name="newproperty" class="form-horizontal span8 offset2" action="newproperty" method="POST">
	<fieldset>
			<legend>Agregar Nueva Propiedad</legend>
			<div class="control-group">  
           		<label class="control-label" for="type">Seleccione un tipo de inmueble </label>  
            		<div class="controls">  
            			<select id="type">  
               				<option>Departamento</option>  
               				<option>Casa</option>  
						</select>  
					</div>  
			</div>
			<div class="control-group">  
           		<label class="control-label" for="operation">Seleccione un tipo de operacion </label>  
            		<div class="controls">  
            			<select id="operation">  
               				<option>Alquiler</option>  
               				<option>Venta</option>  
						</select>  
					</div>  
			</div>
			<div class="control-group">
				<label class="control-label" for="address">Direccion </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="address" name="address"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="neighborhood">Barrio </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="neighborhood" name="neighborhood"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="price">Precio </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="price" name="price"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="spaces">Cantidad de ambientes </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="spaces" name="spaces"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="coveredArea">Superficie cubierta </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="coveredArea" name="coveredArea"/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="freeArea">Superficie descubierta </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="freeArea" name="freeArea"/>
					<p>Metros cuadrados</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="age">Antiguedad </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="age" name="age"/>
					<p>Años</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="description">Descripcion </label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="description">
				</div>
			</div>
			<div class="control-group">  
            <label class="control-label" for="service">Seleccione los servicios disponibles </label>  
            <div class="controls">  
              <label class="checkbox">  
                <input type="checkbox" id="service" value="cable">  
                Cable
              </label>
              <label class="checkbox">  
                <input type="checkbox" id="service" value="telephone">  
                Telefono
              </label>
              <label class="checkbox">  
                <input type="checkbox" id="service" value="swimmingpool">  
                Pileta
              </label>
              <label class="checkbox">  
                <input type="checkbox" id="service" value="lobby">  
                Salon
              </label>
              <label class="checkbox">  
                <input type="checkbox" id="service" value="paddle">  
                Cancha de paddle
              </label>
              <label class="checkbox">  
                <input type="checkbox" id="service" value="quincho">  
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