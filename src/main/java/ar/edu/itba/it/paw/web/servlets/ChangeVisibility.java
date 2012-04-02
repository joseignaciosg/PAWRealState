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

public class ChangeVisibility extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final PropertyService serv = ServiceProvider.getPropertyService();
		final List<String> errors = new ArrayList<String>();
		final Property prop = serv.getPropertyByID(
				Integer.valueOf(req.getParameter("id")), errors);
		if (prop.getVisible() == true) {
			prop.setVisible(false);
		} else {
			prop.setVisible(true);
		}
		req.setAttribute("errors", errors);
		System.out.println("inside ChangeVisibility");
		HTMLUtils.render("myproperties/myproperties.jsp", req, resp);
	}

}
