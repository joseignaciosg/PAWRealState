package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@SuppressWarnings("serial")
public class NewPropertyPage extends HttpServlet {
	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		HTMLUtils.render("myproperties/newproperty.jsp", req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final List<String> errors = new ArrayList<String>();

		final PropertyService service = ServiceProvider.getPropertyService();
		final User currentUser = (User) req.getAttribute("current_user");
		boolean saved = false;
		try {
			saved = service
					.saveProperty(
							req.getParameter("property_operation"),
							req.getParameter("property_type"),
							req.getParameter("property_neighborhood"),
							req.getParameter("property_address"),
							Integer.valueOf(req.getParameter("property_price")),
							Integer.valueOf(req.getParameter("property_spaces")),
							Integer.valueOf(req
									.getParameter("property_coveredArea")),
							Integer.valueOf(req
									.getParameter("property_freeArea")),
							Integer.valueOf(req.getParameter("property_age")),
							new Services(
									Boolean.valueOf(req
											.getParameter("property_cable")),
									Boolean.valueOf(req
											.getParameter("property_telephone")),
									Boolean.valueOf(req
											.getParameter("property_swimmingpool")),
									Boolean.valueOf(req
											.getParameter("property_lobby")),
									Boolean.valueOf(req
											.getParameter("property_paddle")),
									Boolean.valueOf(req
											.getParameter("property_quincho"))),
							req.getParameter("property_description"), errors,
							currentUser);
		} catch (final NumberFormatException e) {
			errors.add("Parámetros inválidos");
		}

		if (saved) {
			resp.sendRedirect(req.getContextPath() + "/myproperties");
		} else {
			req.setAttribute("errors", errors);
			this.doGet(req, resp);
		}

	}
}
