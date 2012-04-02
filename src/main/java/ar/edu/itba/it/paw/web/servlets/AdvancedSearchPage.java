package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/*
 * El usuario debe disponer de busquedas avanzadas para poder 
 * realizar búsquedas con varios tipos de parametros. Para esto
 * necesita una página en donde puede ingresar todos los datos y 
 * recibir una respuesta.
 * 
 * @author Nico
 * 
 */

@SuppressWarnings("serial")
public class AdvancedSearchPage extends HttpServlet {

	@Override
	protected void doGet(final javax.servlet.http.HttpServletRequest req,
			final javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		HTMLUtils.render("search/search.jsp", req, resp);
	};

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		Operation op = null;
		if (req.getParameter("operation").equals("Sell")) {
			op = Operation.SELL;
		} else if (req.getParameter("operation").equals("Rent")) {
			op = Operation.RENT;
		}
		Type type = null;
		if (req.getParameter("type").equals("House")) {
			type = Type.HOUSE;
		} else if (req.getParameter("type").equals("Apartament")) {
			type = Type.APARTMENT;
		}
		Order order = null;
		if (req.getParameter("order").equals("Asc")) {
			order = Order.ASC;
		} else if (req.getParameter("order").equals("Desc")) {
			order = Order.DESC;
		}

		int pricelow;
		int pricehigh;

		try {
			pricelow = Integer.parseInt(req.getParameter("pricelow"));
		} catch (final Exception e) {
			pricelow = -1;
		}
		try {
			pricehigh = Integer.parseInt(req.getParameter("pricehigh"));
		} catch (final Exception e) {
			pricehigh = -1;
		}

		final PropertyService serv = ServiceProvider.getPropertyService();
		final List<Property> props = serv.advancedSearch(op, type, pricelow,
				pricehigh, 0, 10, order);
		System.out.println("operation:" + op);
		System.out.println("type: " + type);
		System.out.println("pricelow: " + pricelow);
		System.out.println("pricehigh: " + pricehigh);
		System.out.println("order: " + order);
		System.out.println(props);
		req.setAttribute("props", props);
		HTMLUtils.render("search/searchresult.jsp", req, resp);

	}
}
