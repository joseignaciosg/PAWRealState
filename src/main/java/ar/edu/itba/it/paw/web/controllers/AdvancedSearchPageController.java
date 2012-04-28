package ar.edu.itba.it.paw.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.model.services.ServiceProvider;

/*
 * El usuario debe disponer de busquedas avanzadas para poder 
 * realizar búsquedas con varios tipos de parametros. Para esto
 * necesita una página en donde puede ingresar todos los datos y 
 * recibir una respuesta.
 * 
 * @author Nico
 * 
 */
@Controller
@RequestMapping("/search")
public class AdvancedSearchPageController {

	@RequestMapping(method = RequestMethod.POST)
	protected void search(@RequestParam("propertyId") final Property property,
			final HttpServletRequest req) {

		Integer page;
		try {
			page = Integer.valueOf(req.getParameter("page"));
		} catch (final Exception e) {
			page = 0;
		}
		Operation op = null;
		if (req.getParameter("operation") != null
				&& req.getParameter("operation").equals("Sell")) {
			op = Operation.SELL;
		} else if (req.getParameter("operation") != null
				&& req.getParameter("operation").equals("Rent")) {
			op = Operation.RENT;
		}
		Type type = null;
		if (req.getParameter("type") != null
				&& req.getParameter("type").equals("House")) {
			type = Type.HOUSE;
		} else if (req.getParameter("type") != null
				&& req.getParameter("type").equals("Apartment")) {
			type = Type.APARTMENT;
		}
		Order order = null;
		if (req.getParameter("order") != null
				&& req.getParameter("order").equals("Asc")) {
			order = Order.ASC;
		} else if (req.getParameter("order") != null
				&& req.getParameter("order").equals("Desc")) {
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
				pricehigh, Integer.valueOf(page), 5, order);

		req.setAttribute("props", props);
		req.setAttribute("pagenum", page);
		req.setAttribute("operation", req.getParameter("operation"));
		req.setAttribute("type", req.getParameter("type"));
		req.setAttribute("pricelow", req.getParameter("pricelow"));
		req.setAttribute("pricehigh", req.getParameter("pricehigh"));
		req.setAttribute("order", req.getParameter("order"));
		// HTMLUtils.render("search/search.jsp", req, resp);

	}
}
