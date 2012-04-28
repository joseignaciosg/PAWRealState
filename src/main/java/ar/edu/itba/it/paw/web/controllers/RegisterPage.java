package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

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

	private String registerPage = "/register/register.jsp";

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		HTMLUtils.render(this.registerPage, req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final UserService service = ServiceProvider.getUserService();

		final List<String> errors = new ArrayList<String>();

		final boolean valid = service.register(
				req.getParameter("user_first_name"),
				req.getParameter("user_last_name"),
				req.getParameter("user_email"), req.getParameter("user_phone"),
				req.getParameter("user_username"),
				req.getParameter("user_password"),
				req.getParameter("user_password_repeated"), errors);

		if (valid) {
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			req.setAttribute("errors", errors);
			this.doGet(req, resp);
		}

	}
}
