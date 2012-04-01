<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="row">
	<form name="register" class="form-horizontal span8 offset2" action="register" method="POST">
		<fieldset>
			<legend>Registro de Usuario</legend>
			<div class="control-group">
				<label class="control-label" for="user_username">Nombre de Usuario</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_username" name="user_username">
					<p class="help-block">Su nombre de usuario para ingresar al sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_password">Contraseña</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="user_password" name="user_password">
					<p class="help-block">Su contraseña para ingreso al sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_password">Repetir contraseña</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="user_password_repeated" name="user_password_repeated">
					<p class="help-block">Repitala para evitar errores</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_email">Email</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_email" name="user_email">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_first_name">Nombre</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_first_name" name="user_first_name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_last_name">Apellido</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_last_name" name="user_last_name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_phone">Teléfono</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_phone" name="user_phone">
				</div>
			</div>
			<div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Registrarse"/>
            <button class="btn">Limpiar</button>
          </div>
		</fieldset>
	</form>
</div>