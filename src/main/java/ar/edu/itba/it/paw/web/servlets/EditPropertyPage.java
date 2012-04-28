package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/**
 * Los usuarios registrados deben poder editar los avisos publicados, pudiendo
 * modificar toda la inforrmación básica del punto anterior. Para esto deben
 * disponer de una página en donde se listen sus propiedades publicadas.
 * 
 * @author cris
 * 
 */

@SuppressWarnings("serial")
public class EditPropertyPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final PropertyService service = ServiceProvider.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		final Property property = service.getPropertyByID(
				Integer.valueOf(req.getParameter("ID")), errors);

		req.setAttribute("property", property);

		HTMLUtils.render("/myproperties/editproperty.jsp", req, resp);
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
			saved = service.saveProperty(
					req.getParameter("property_operation"),
					req.getParameter("property_type"),
					req.getParameter("property_neighborhood"),
					req.getParameter("property_address"),
					Integer.valueOf(req.getParameter("property_price")),
					Integer.valueOf(req.getParameter("property_spaces")),
					Integer.valueOf(req.getParameter("property_coveredArea")),
					Integer.valueOf(req.getParameter("property_freeArea")),
					Integer.valueOf(req.getParameter("property_age")),
					new Services(req.getParameter("property_cable") != null,
							req.getParameter("property_telephone") != null,
							req.getParameter("property_swimmingpool") != null,
							req.getParameter("property_lobby") != null, req
									.getParameter("property_paddle") != null,
							req.getParameter("property_quincho") != null), req
							.getParameter("property_description"), errors,
					currentUser, Integer.valueOf(req.getParameter("ID")));
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
