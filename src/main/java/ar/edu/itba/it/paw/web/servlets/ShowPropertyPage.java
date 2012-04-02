package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/*
 * Todos los usuarios, incluyendo los anónimos, deben poder 
 * desde el detalle de un aviso consultar los datos de contacto 
 * del publicador. Para ello, deben indicar su nombre, email, 
 * teléfono y un comentario opcional. Al completar estos datos 
 * se le debe mostrar al usuario el teléfono y el email del 
 * publicador.
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class ShowPropertyPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final List<String> errors = new ArrayList<String>();
		final Integer ID = Integer.valueOf(req.getParameter("id"));
		System.out.println("id :" + ID);
		final PropertyService propservice = ServiceProvider
				.getPropertyService();
		final Property property = propservice.getPropertyByID(ID, errors);
		if (property == null) {
			req.setAttribute("errors", errors);
			HTMLUtils.render("/viewproperties/viewproperty.jsp", req, resp);
		} else {
			req.setAttribute("property", property);
			HTMLUtils.render("/viewproperties/viewproperty.jsp", req, resp);

		}

	}
}
