<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="offset3 span6">
		<h3>${ hotel.name }</h3>
		<hr />
	</div>
</div>
<div class="row">
	<div class="offset3 span6">
		<p class="well">${ hotel.description }</p>
		<hr />
	</div>
</div>
<div class="row">
	<div class="offset3 span6">
		<h5>Comentarios</h5>
		<hr />
	</div>
	<c:forEach var="comment" items="${ hotel.comments }">
		<div class="offset3 span6">
			<div class="well">
				<h6>Autor: ${ comment.user.userName } (${ comment.user.email })</h6>
				<p>${ comment.content }</p>
			</div>
		</div>
	</c:forEach>
	<div class="offset3 span6">
		<form action='${ basePath }/comments' method='POST'
			class="form form-inline">
			<input type='hidden' name='hotel_id' class="input-xlarge"
				value="${ hotel.id }" /> <input type='text' name='comment_content'
				class="input-xlarge" placeholder="Su comentario" /> <input
				type="submit" class="btn btn-primary" value="Crear comentario" />
		</form>
	</div>

</div>