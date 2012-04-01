<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="row">
	<form class="form-horizontal span8 offset2">
		<fieldset>
			<legend>Registro de Usuario</legend>
			<div class="control-group">
				<label class="control-label" for="username">Nombre de Usuario</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="username">
					<p class="help-block">Su nombre de usuario para ingresar al sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">Contraseña</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="password">
					<p class="help-block">Su contraseña para ingreso al sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password_check">Email</label>
				<div class="controls">
					<input type="password_check" class="input-xlarge" id="password_check">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password_check">Nombre</label>
				<div class="controls">
					<input type="password_check" class="input-xlarge" id="password_check">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password_check">Apellido</label>
				<div class="controls">
					<input type="password_check" class="input-xlarge" id="password_check">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password_check">Teléfono</label>
				<div class="controls">
					<input type="password_check" class="input-xlarge" id="password_check">
				</div>
			</div>
			<div class="form-actions">
            <button type="submit" class="btn btn-primary">Registrarse</button>
            <button class="btn">Limpiar</button>
          </div>
		</fieldset>
	</form>
</div>