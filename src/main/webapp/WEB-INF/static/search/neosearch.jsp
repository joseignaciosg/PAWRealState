<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="subnav subnav-fixed">
<form class="navbar-form pull-center" method="post" action="search">  
<fieldset>  
    <ul class="nav nav-pills">
      <li ><legend><span class="label label-info">Precio Desde:</span></legend></li>
      <li>
      	<c:if test = "${pricelow == -1}">  
        	<input type="text" name="pricelow" class="span2"  style="width:50px">
         </c:if>
        <c:if test = "${pricelow != -1}">   
             <input type="text" name="pricelow" class="span2" value='<c:out value="${pricelow}"/>' style="width:50px">
		 </c:if>
      </li>
      <li class="divider-vertical"></li>
      <li><legend><span class="label label-info">Precio Hasta:</span></legend></li>
      <li>
     	 <c:if test = "${pricehigh == -1}">  
        	<input type="text" name="pricehigh" class="span2"  style="width:50px">
         </c:if>
        <c:if test = "${pricehigh != -1}">   
             <input type="text" name="pricehigh" class="span2" value='<c:out value="${pricehigh}"/>' style="width:50px">
		 </c:if>
        
      </li>
      <li class="divider-vertical"></li>
      <li><legend><span class="label label-info">Operación:</span></legend></li>
      <li>
    	  <select id="operation" name="operation"  style="width:100px" > 
              	<option value="All" >Todos</option> 
              	 <c:if test = "${operation == 'Sell'}">   
                	<option value="Sell" selected="selected">Venta</option>  
				 </c:if>
				 <c:if test = "${operation != 'Sell'}">   
                	<option value="Sell" >Venta</option>  
				 </c:if>
				 <c:if test = "${operation == 'Rent'}">   
                	<option value="Rent" selected="selected">Alquiler</option>  
				 </c:if>
				 <c:if test = "${operation != 'Rent'}">   
               		<option value="Rent" >Alquiler</option>  
				 </c:if>  
              </select>  
      </li>
      <li class="divider-vertical"><hr/></li>
      <li><legend><span class="label label-info">Tipo de Propiedad:</span></legend></li>
      <li>
      	  <select id="type" name="type" style="width:100px">  
               	<option value="All" >Todos</option> 
               	<c:if test = "${type == 'House'}">   
               		 <option value="House" selected="selected" >Casa</option>  
				</c:if>
				<c:if test = "${type != 'House'}">   
               		 <option value="House">Casa</option>  
				</c:if>
				<c:if test = "${type == 'Apartment'}">   
                	<option value="Apartment" selected="selected">Departamento</option>  
				</c:if>
				<c:if test = "${type != 'Apartment'}">   
                	<option value="Apartment">Departamento</option>  
				</c:if>
          </select> 
      </li>
       <li class="divider-vertical"></li>
      <li><legend><span class="label label-info">Órden:</span></legend></li>
      <li>
           <select id="order" name="order" style="width:100px">
          		<c:if test = "${order == 'Asc'}">   
               		 <option value="Asc" selected="selected">Ascendente</option>  
				</c:if>
				<c:if test = "${order != 'Asc'}">   
               		 <option value="Asc">Ascendente</option>  
				</c:if>
                <c:if test = "${order == 'Desc'}">   
               		 <option value="Desc" selected="selected">Descendiente</option>  
				</c:if>
				<c:if test = "${order != 'Desc'}">   
               		 <option value="Desc">Descendiente</option> 
				</c:if>
           </select> 
      </li>
      <li class="divider-vertical"></li>
      <li class="divider-vertical"></li>
      <li>
     	 <input type="hidden" value="0" name="page" />
      	<input type="submit" class="btn btn-primary" value="Buscar">
      </li>
</fieldset> 
</form>
    </ul>
 </div>
 
 <c:if test="${empty props }">   
 <br/>  
	<legend>No hay resultados para la búsqueda realidada.</legend>   
</c:if>

<c:if test="${! empty props  }">     
<br/>
<br/>

<c:forEach var="prop" items="${props}" varStatus="i">
	<div class="hero-unit">
	<div class="row">
		<div class="span1">
			<p style="color:#27702A; font-size:30px;"><c:out value="${i.count}"/></p>
		</div>
		<div class="span2">
			<img height="100" width="100" src="http://www.highgrowthpropertyinvestment.co.uk/media/Sell-Property.jpg" alt="house" />
		</div>
		<div class="span4">
				<b>Operación:</b> 
				 <c:if test = "${prop.operation == 'SELL'}">   
							En Venta
						</c:if>
						<c:if test = "${prop.operation == 'RENT'}">   
							En Alquiler
						</c:if>
				<br>
				<b>Tipo de Propiedad:</b> 
						<c:if test = "${prop.type == 'HOUSE'}">   
							Casa
						</c:if>
						<c:if test = "${prop.type == 'APARTMENT'}">   
							Departamento
						</c:if><br>
				<b>Dirección:</b> </b><c:out value="${prop.address}" /><br>
				<b>Barrio:</b> <c:out value="${prop.neighborhood}" /><br>
		</div>
		<div class="span2">
			<p style="color:#27702A; font-size:30px;">$<c:out value=" ${prop.price}" /><p>					
		 	<a class="btn" href='${ basePath }/properties/view?id=<c:out value="${prop.id}"/>'>detalle</a>
		 </div>
	 
	 </div>
	 </div>
</c:forEach>


</c:if>

	<div class="pagination subnavbottom">
	  <ul>
	      <c:if test = "${pagenum-1 >= 0}">   
	     	 <li><a href='search?page=<c:out value="${pagenum-1}"/>&operation=<c:out value="${operation}"/>&type=<c:out value="${type}"/>&pricelow=<c:out value="${pricelow}"/>&pricehigh=<c:out value="${pricehigh}"/>&order=<c:out value="${order}"/>'>Anterior</a></li>
		  </c:if>
		  <c:if test="${! empty props  }">     
	      	<li><a href='search?page=<c:out value="${pagenum+1}"/>&operation=<c:out value="${operation}"/>&type=<c:out value="${type}"/>&pricelow=<c:out value="${pricelow}"/>&pricehigh=<c:out value="${pricehigh}"/>&order=<c:out value="${order}"/>'>Siguiente</a></li>
		  </c:if>
	  </ul>
	</div>
