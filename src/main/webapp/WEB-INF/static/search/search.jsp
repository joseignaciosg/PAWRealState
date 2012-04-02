<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<form class="form-horizontal" method="post" action="search">  
        <fieldset>  
          <legend>Búsqueda Avanzada de Propiedades</legend>  
          <div class="control-group">  
          	<h4>Rango de Precios</h4>
            <label class="control-label" for="pricelow">Desde</label>  
            <div class="controls">  
              <input type="text" class="input-xlarge" id="pricelow" name="pricelow">  
            </div>  
          
            <label class="control-label" for="pricehigh">Hasta</label>  
            <div class="controls">  
              <input type="text" class="input-xlarge" id="pricehigh" name="pricehigh">  
            </div>  
          </div>  
          
          <div class="control-group">  
            <label class="control-label" for="operation">Operación</label>  
            <div class="controls">  
              <select id="operation" name="operation"> 
              	<option>Todos</option>  
                <option>Venta</option>  
                <option>Alquiler</option>  
              </select>  
            </div>  
          </div>
          <div class="control-group">  
            <label class="control-label" for="type">Tipo de Propiedad</label>  
            <div class="controls">  
              <select id="type" name="type">  
               	<option>Todos</option>  
                <option>Casa</option>  
                <option>Departamento</option>  
              </select>  
            </div>  
          </div>  
          <div class="control-group">  
            <label class="control-label" for="order">Órden</label>  
            <div class="controls">  
              <select id="order" name="order">  
                <option>Ascendente</option>  
                <option>Descendiente</option>  
              </select>  
            </div>  
          </div>   
          <div class="form-actions">  
            <button type="submit" class="btn btn-primary">Buscar</button>  
          </div>  
        </fieldset>  
</form>