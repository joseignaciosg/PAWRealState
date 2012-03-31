package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Los usuarios deben poder registrarse en el sistema indicando su nombre,
 * apellido, email, teléfono, usuario y contraseña. Todos los campos son
 * obligatorios. La contraseña debe ingresarse dos veces para verificar que esté
 * correctamente escrita. No puede haber nombres de usuario repetidos.
 * 
 * @author cris
 * 
 */

@SuppressWarnings("serial")
public class RegisterPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		resp.getWriter().write(":D");
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
