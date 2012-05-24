<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<br/>
<div class="row">
	<div class="offset3 span6">
		<c:if test="${ not empty errors }">
			<c:forEach var="error" items="${ errors }">
				<div class="alert alert-error">${ error }</div>
			</c:forEach>
		</c:if>
		<c:if test="${ not empty successes }">
			<c:forEach var="success" items="${ successes }">
				<div class="alert alert-success">${ success }</div>
			</c:forEach>
		</c:if>
	</div>
</div>