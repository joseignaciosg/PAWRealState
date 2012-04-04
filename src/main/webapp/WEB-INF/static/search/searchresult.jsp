<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${empty props }">     
	<legend>No hay resultados para la búsqueda realidada.</legend>   
	<div class="form-actions">
		<button type="button" class="btn btn-primary"><a href="search">Atrás</a></button>
	</div>                       
</c:if>

<c:if test="${! empty props  }">     

<legend>Resultados de la básqueda avanzada.</legend>   

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
				<b>Operación:</b> <c:out value=" ${prop.operation}" /><br>
				<b>Tipo de Propiedad:</b> <c:out value="${prop.type}" /><br>
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
<div class="form-actions">
		<button type="button" class="btn btn-primary"><a href="search">Atrás</a></button>
</div>

</c:if>
		

