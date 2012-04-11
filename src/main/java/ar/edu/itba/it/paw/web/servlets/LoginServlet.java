package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.UserManager;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5309533354100266135L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		resp.sendRedirect(req.getContextPath() + "/index");
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final String username = req.getParameter("user_username");
		final String password = req.getParameter("user_password");
		final String remember = req.getParameter("remember");

		final UserManager manager = (UserManager) req
				.getAttribute("userManager");

		final UserService service = ServiceProvider.getUserService();

		final boolean loginValid = service.login(username, password, manager);

		if (loginValid) {
			final CookiesManager cookman = new CookiesManager(req, resp);
			cookman.setUser(username, password, remember);
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			final List<String> errors = new ArrayList<String>();

			errors.add("Usuario/Contraseña inválidos");

			req.setAttribute("errors", errors);

			req.getRequestDispatcher("/index").forward(req, resp);
		}
	}

}
