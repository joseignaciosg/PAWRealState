package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public void index(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final CookiesManager manager = new CookiesManager(req, resp);
		final PropertyService service = ServiceProvider.getPropertyService();

		final List<Property> rentProperties = service.advancedSearch(
				Operation.RENT, null, -1, -1, 0, 2, Order.DESC);
		final List<Property> sellProperties = service.advancedSearch(
				Operation.SELL, null, -1, -1, 0, 2, Order.DESC);

		req.setAttribute("rentProperties", rentProperties);
		req.setAttribute("sellProperties", sellProperties);

		req.setAttribute("user_cookie_username", manager.getName());
		req.setAttribute("user_remember", manager.getRemember());

		HTMLUtils.render("index/index.jsp", req, resp);
	}

}
