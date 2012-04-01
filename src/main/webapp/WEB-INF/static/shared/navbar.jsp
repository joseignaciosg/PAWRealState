<%@ page isELIgnored="false"%>
<a class="brand" href="#"> ChinuProp </a>
<ul class="nav pull-right">
	<li>
		<form class="navbar-form pull-right" action="${ basePath }/login" method="POST">
			<input type="text" name="user_username" class="span2" placeholder="Usuario" /> <input
				type="password" name="user_password"  class="span2" placeholder="Contraseña" /> <input
				type="submit" class="btn btn-primary" value="Entrar" />
		</form>
	</li>
	<li class="divider-vertical"></li>
	<li><a href="/register">Registrarse</a></li>
</ul>
