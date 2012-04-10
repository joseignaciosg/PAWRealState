package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

public class MyPropertiesDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 5705690655250406540L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final List<String> errors = new ArrayList<String>();
		boolean valid = false;
		try {
			final Integer id = Integer.valueOf(req.getParameter("ID"));
			final User currentUser = (User) req.getAttribute("current_user");

			final PropertyService service = ServiceProvider
					.getPropertyService();

			valid = service.deleteProperty(id, currentUser, errors);

		} catch (final Exception e) {
			errors.add("Parámetro inválido");
		}

		if (!valid) {
			req.setAttribute("errors", errors);
			HTMLUtils.redirectBack(req, resp);
		} else {
			HTMLUtils.redirectBack(req, resp);
		}

	}
}
