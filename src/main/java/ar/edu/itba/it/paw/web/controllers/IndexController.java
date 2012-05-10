package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private HibernatePropertyRepository repository;

	@RequestMapping(method = RequestMethod.POST, value = "/index")
	public ModelAndView indexPOST(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		return this.index(req, resp);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final CookiesManager manager = new CookiesManager(req, resp);

		final List<Property> rentProperties = this.repository
				.getAll(new PropertySearch(Operation.RENT));
		final List<Property> sellProperties = this.repository
				.getAll(new PropertySearch(Operation.SELL));

		final ModelAndView mav = new ModelAndView("index/index");

		mav.getModelMap().put("rentProperties", rentProperties);
		mav.getModelMap().put("sellProperties", sellProperties);

		mav.getModelMap().put("user_cookie_username", manager.getName());
		mav.getModelMap().put("user_remember", manager.getRemember());

		return mav;
	}
}
