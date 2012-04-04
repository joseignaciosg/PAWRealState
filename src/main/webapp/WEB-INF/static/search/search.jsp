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
              	<option value="All" >Todos</option>  
                <option value="Sell" >Venta</option>  
                <option value="Rent" >Alquiler</option>  
              </select>  
            </div>  
          </div>
          <div class="control-group">  
            <label class="control-label" for="type">Tipo de Propiedad</label>  
            <div class="controls">  
              <select id="type" name="type">  
               	<option value="All" >Todos</option>  
                <option value="House" >Casa</option>  
                <option value="Apartment">Departamento</option>  
              </select>  
            </div>  
          </div>  
          <div class="control-group">  
            <label class="control-label" for="order">Órden</label>  
            <div class="controls">  
              <select id="order" name="order">  
                <option value="Asc">Ascendente</option>  
                <option value="Desc">Descendiente</option>  
              </select>  
            </div>  
          </div>   
          <div class="form-actions">  
            <button type="submit" class="btn btn-primary">Buscar</button>  
          </div>  
        </fieldset>  
</form>