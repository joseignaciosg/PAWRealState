<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a class="brand" href="${ basePath }/"> ChinuProp </a>
<ul class="nav pull-right">
	<c:if test="${ empty current_user }">
		<li>
			<form class="navbar-form pull-right span7" action="${ basePath }/login"
				method="POST">
				<div class="pull-right">
				<input type="hidden" name="remember" id="remember" value="name"/>
             	<input type="text" name="user_username" class="span2" value="${ user_cookie_username }" 
					placeholder="Usuario" /> <input type="password"
					name="user_password" class="span2" placeholder="Contrase&ntilde;a" /> <input
					type="submit" class="btn btn-primary" value="Entrar" />
				</div>
				<!--<div class="pull-right" style="text-align:right; margin-top:10px; margin-right:5px;">
					nombre
					<input type="checkbox" name="user_remember" id="rememberme" />
				</div>-->
				<div data-toggle="buttons-radio" id="remember" class="btn-group pull-right tabs" style="margin-right:5px;">
                    <input type="button" class="btn btn-primary active" id="remember_name" name="remember_name" value="Nombre"/>
                    <input type="button" class="btn btn-primary" id="remember_session" name="remember_session" value="Sesi&oacute;n"/>
                  </div>				
				<div style="margin-top:10px;">
					Recordar:
				</div>
                	   
				
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
					<a href="${ basePath }/logout"> Salir </a>
				</li>
			</ul>
		</li>
	</c:if>
</ul>
