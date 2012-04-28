package ar.edu.itba.it.paw.web.controllers;

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

public class PhotoListPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final PropertyService service = ServiceProvider.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		final Property property = service.getPropertyByID(
				Integer.valueOf(req.getParameter("propertyId")), errors);

		req.setAttribute("property", property);

		if (errors.size() > 0) {
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			HTMLUtils.render("myproperties/myphotos.jsp", req, resp);
		}
	}
}
