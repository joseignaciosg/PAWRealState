<form class="form-horizontal" method="post" action="search">  
        <fieldset>  
          <legend>Properties Advanced Search</legend>  
          <div class="control-group">  
          	<h4>Price Range</h4>
            <label class="control-label" for="pricelow">From</label>  
            <div class="controls">  
              <input type="text" class="input-xlarge" id="pricelow" name="pricelow">  
            </div>  
          
            <label class="control-label" for="pricehigh">To</label>  
            <div class="controls">  
              <input type="text" class="input-xlarge" id="pricehigh" name="pricehigh">  
            </div>  
          </div>  
          
          <div class="control-group">  
            <label class="control-label" for="operation">Operation</label>  
            <div class="controls">  
              <select id="operation" name="operation"> 
              	<option>All</option>  
                <option>Sell</option>  
                <option>Rent</option>  
              </select>  
            </div>  
          </div>
          <div class="control-group">  
            <label class="control-label" for="type">Type</label>  
            <div class="controls">  
              <select id="type" name="type">  
               	<option>All</option>  
                <option>House</option>  
                <option>Apartment</option>  
              </select>  
            </div>  
          </div>  
          <div class="control-group">  
            <label class="control-label" for="order">Show Order</label>  
            <div class="controls">  
              <select id="order" name="order">  
                <option>Asc</option>  
                <option>Desc</option>  
              </select>  
            </div>  
          </div>   
          <div class="form-actions">  
            <button type="submit" class="btn btn-primary">Search</button>  
          </div>  
        </fieldset>  
</form>