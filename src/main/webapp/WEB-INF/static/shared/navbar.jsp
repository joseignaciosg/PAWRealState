<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a class="brand" href="${ basePath }/"> ChinuProp </a>
<ul class="nav pull-right">
	<c:if test="${ empty current_user }">
		<li>
			<form class="navbar-form pull-right" action="${ basePath }/login"
				method="POST">
				<input type="text" name="user_username" class="span2" value="${ user_cookie_username }" 
					placeholder="Usuario" /> <input type="password"
					name="user_password" class="span2" placeholder="Contraseña" /> <input
					type="submit" class="btn btn-primary" value="Entrar" />
			</form>
		</li>
		<li class="divider-vertical"></li>
		<li><a href="/register">Registrarse</a></li>
	</c:if>
	<c:if test="${ not empty current_user }">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				${ current_user.username }
				<b class="caret"></b>
			</a> 
			<ul class="dropdown-menu">
				<li>
					<a href="${ basePath }/myproperties"> Mis Propiedades </a>
				</li>
				<li>
					<a href="${ basePath }/myproperties/new"> Crear Propiedad </a>
				</li>
				<li>
					<a href="${ basePath }/mycontactpetitions"> Mis Peticiones de Contacto </a>
				</li>
				<li>
					<a href="${ basePath }/logout"> Salir </a>
				</li>
			</ul>
		</li>
	</c:if>
</ul>
