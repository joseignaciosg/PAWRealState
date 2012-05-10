<%@ page pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a class="brand" href="${ basePath }/"> ChinuProp </a>

<ul class="nav pull-right">
	<c:if test="${ empty current_user }">
		<li>
			<form:form class="navbar-form pull-right span7" action="${ basePath }/user/login" commandName="loginForm" method="POST">
				<div class="pull-right">
					<input type="hidden" name="remember" id="remember" value="name"/>
             		<input type="text" name="user_username" class="span2" value="${ user_cookie_username }" placeholder="Usuario" />
             		<input type="password" name="user_password" class="span2" placeholder="Contrase&ntilde;a"/>
             	<input type="submit" class="btn btn-primary" value="Entrar" />
				</div>
				<div data-toggle="buttons-radio" id="remember" class="btn-group pull-right tabs remember-me">
                    <input type="button" class="btn btn-primary active" id="remember_name" name="remember_name" value="Nombre"/>
                    <input type="button" class="btn btn-primary" id="remember_session" name="remember_session" value="Sesi&oacute;n"/>
                  </div>				
				<div class="remember-me-label" >
					Recordar:
				</div>
                	   
				
			</form:form>
		</li>
		<li class="divider-vertical"></li>
		<li><a href="${ basePath }/user/register">Registrarse</a></li>
	</c:if>
	<c:if test="${ not empty current_user }">
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				${ current_user.username }
				<b class="caret"></b>
			</a> 
			<ul class="dropdown-menu">
				<li>
					<a href="${ basePath }/property/list"> Mis Propiedades </a>
				</li>
				<li>
					<a href="${ basePath }/property/new"> Crear Propiedad </a>
				</li>
				<li>
					<a href="${ basePath }/user/logout">Salir </a>
				</li>
			</ul>
		</li>
	</c:if>
</ul>
