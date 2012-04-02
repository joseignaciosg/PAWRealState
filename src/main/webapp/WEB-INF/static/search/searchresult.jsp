<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${empty props }">     
	<legend>No results for the specified search.</legend>   
	<div class="form-actions">
		<button type="button" class="btn btn-primary"><a href="search">Back</a></button>
	</div>                       
</c:if>

<c:if test="${! empty props  }">     

<legend>Advanced Search Results</legend>   

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
				<b>Operation Type:</b> <c:out value=" ${prop.operation}" /><br>
				<b>Property Type:</b> <c:out value="${prop.type}" /><br>
				<b>Address:</b> </b><c:out value="${prop.address}" /><br>
				<b>Neighborhood:</b> <c:out value="${prop.neighborhood}" /><br>
		</div>
		<div class="span2">
			<p style="color:#27702A; font-size:30px;">$<c:out value=" ${prop.price}" /><p>					
		 	<a class="btn" href='${ basePath }/properties/view?id=<c:out value="${prop.id}"/>'>detail</a>
		 </div>
	 
	 </div>
	 </div>
</c:forEach>
<div class="form-actions">
		<button type="button" class="btn btn-primary"><a href="search">Back</a></button>
</div>

</c:if>
		

