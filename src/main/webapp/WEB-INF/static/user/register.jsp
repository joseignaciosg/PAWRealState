<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<form:form class="form-horizontal span8 offset2" method="post"
		action="register" commandName="registrationForm" enctype="multipart/form-data">
		<form:errors path="*" />
		<fieldset>
			<legend>Registro de Usuario</legend>
			<div class="control-group">
				<label class="control-label" for="user_username">Nombre de
					Usuario</label>
				<div class="controls">
					<form:input type="text" class="input-xlarge" id="user_username"
						name="userName" path="userName" value="${ registerForm.userName }" />
					<p class="help-block">Su nombre de usuario para ingresar al
						sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_password">Contraseña</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="user_password"
						name="password" path="password" value="${ registerForm.password }">
					<p class="help-block">Su contraseña para ingreso al sitio</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_password">Repetir
					contraseña</label>
				<div class="controls">
					<input type="password" class="input-xlarge"
						id="user_password_repeated" name="repeatedPassword"
						path="repeatedPassword" value="${ registerForm.repeatedPassword }">
					<p class="help-block">Repitala para evitar errores</p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_email">Email</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_email"
						name="email" path="email" value="${ registerForm.email }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_first_name">Nombre</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_first_name"
						name="firstName" path="firstName"
						value="${ registerForm.firstName }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_last_name">Apellido</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_last_name"
						name="lastName" path="lastName" value="${ registerForm.lastName }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_phone">Teléfono</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_phone"
						name="phone" path="phone" value="${ registerForm.phone }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="user_phone">Tipo de
					Usuario</label>
				<div class="controls">
					<select id="user_type" name="type" class="js-toggle-usertype">
						<option selected="selected" value="User">Usuario
							Particular</option>
						<option value="RealStateAgency">Agente Inmobiliario</option>
					</select>
				</div>
			</div>
			<div class="control-group hidden agency-control">
				<label class="control-label" for="user_phone">Nombre de
					Inmobiliaria</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="user_phone"
						name="agencyName" path="lo" value="${ registerForm.agencyName }">
				</div>
			</div>
			<div class="control-group hidden agency-control">
				<label class="control-label" for="user_phone">Logo</label>
				<div class="controls">
					<input type="file" class="input-xlarge" id="user_logo" name="photo"
						path="logo" value="${ registerForm.agencyName }">
				</div>
			</div>
			<div class="form-actions">
				<input type="submit" class="btn btn-primary" value="Registrarse" />
			</div>
		</fieldset>
	</form:form>
</div>