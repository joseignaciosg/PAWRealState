package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.session.UserManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/**
 * Los usuarios registrados deben poder publicar avisos de venta o alquiler de
 * propiedades. Para realizar la publicación se debe indicar la información
 * básica del aviso: el tipo de inmueble (departamento o casa), el tipo de
 * operación (venta o alquiler), la dirección, el barrio, el precio , la
 * cantidad de ambientes, la superficie cubierta, la superficie descubierta, la
 * antigüedad, los servicios que dispone (cable, teléfono, pileta, salón, cancha
 * de paddle, quincho) y una descripción (único campo optativo). Los servicios
 * que tiene una propiedad no se deben registrar como un texto libre sino que se
 * debe poder indicar si dispone o no de cada uno de ellos.
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class MyPropertiesPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final UserManager userManager = (UserManager) req
				.getAttribute("userManager");
		final UserService service = ServiceProvider.getUserService();

		req.removeAttribute("current_user");
		req.setAttribute("current_user",
				service.getById(userManager.getCurrentUser().getId()));

		HTMLUtils.render("myproperties/myproperties.jsp", req, resp);
	}
}
