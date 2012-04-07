package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.ContactRequestService;
import ar.edu.itba.it.paw.model.services.EmailService;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/*
 * Los usuarios deben poder contactar al dueño de una propiedad para hacerle consultas.
 * Para esto deben disponer de una página en donde puedan ingresar su usuario, mail, 
 * telefono y descripcion opcional y le devuelva la información del usuario una vez 
 * realizada la consulta
 * 
 *  @author Nico
 */

@SuppressWarnings("serial")
public class ContactRequestPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final ContactRequestService service = ServiceProvider
				.getContactRequestService();

		final PropertyService PropService = ServiceProvider
				.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		final String name = req.getParameter("first_name")
				+ req.getParameter("last_name");
		final String email = req.getParameter("email");
		final String telephone = req.getParameter("phone");
		final String description = req.getParameter("description");
		final Integer propID = Integer.valueOf(req.getParameter("property_id"));
		final Property property = PropService.getPropertyByID(propID, errors);

		final boolean valid = service.saveContactRequest(null, name, email,
				telephone, description, property, errors);

		if (valid) {
			final User user = (User) req.getAttribute("current_user");
			final EmailService notification = ServiceProvider.getEmailService();
			notification.sendMail(user, property, errors);
			req.setAttribute("user", user);
			req.setAttribute("property", property);
			HTMLUtils.render("/contactrequest/contactRequest.jsp", req, resp);
		} else {
			req.setAttribute("errors", errors);
			this.doGet(req, resp);
		}
	}
}
