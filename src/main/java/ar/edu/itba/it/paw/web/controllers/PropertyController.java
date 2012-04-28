package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.ContactRequestService;
import ar.edu.itba.it.paw.model.services.EmailService;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.session.UserManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	protected void searchGET(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		this.searchPOST(req, resp);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/search")
	protected void searchPOST(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
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
		HTMLUtils.render("search/search.jsp", req, resp);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected String newGET(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected String newPOST(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException,
			ServletException {
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
					currentUser, null);
		} catch (final NumberFormatException e) {
			errors.add("Parámetros inválidos");
		}

		if (saved) {
			// resp.sendRedirect(req.getContextPath() + "/myproperties");
			return "redirect:..";
		} else {
			req.setAttribute("errors", errors);
			// this.newGET(req, resp);
			return "redirect:new";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String delete(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final List<String> errors = new ArrayList<String>();
		boolean valid = false;
		try {
			final Integer id = Integer.valueOf(req.getParameter("ID"));
			final User currentUser = (User) req.getAttribute("current_user");

			final PropertyService service = ServiceProvider
					.getPropertyService();

			valid = service.deleteProperty(id, currentUser, errors);

		} catch (final Exception e) {
			errors.add("Parámetro inválido");
		}

		if (!valid) {
			req.setAttribute("errors", errors);
			// HTMLUtils.redirectBack(req, resp);
			return "redirect:/bin//index";
		} else {
			// HTMLUtils.redirectBack(req, resp);
			return "redirect:/bin//index";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void changeVisibility(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException {
		final PropertyService serv = ServiceProvider.getPropertyService();
		final List<String> errors = new ArrayList<String>();
		serv.toggleVisibility(Integer.valueOf(req.getParameter("id")), errors);
		req.setAttribute("errors", errors);
		HTMLUtils.redirectBack(req, resp);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected void edit(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException,
			ServletException {
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
			final PropertyService service1 = ServiceProvider
					.getPropertyService();

			final List<String> errors1 = new ArrayList<String>();

			final Property property = service1.getPropertyByID(
					Integer.valueOf(req.getParameter("ID")), errors1);

			req.setAttribute("property", property);

			HTMLUtils.render("/myproperties/editproperty.jsp", req, resp);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	protected void show(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final List<String> errors = new ArrayList<String>();
		final Integer ID = Integer.valueOf(req.getParameter("id"));
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

	@RequestMapping(method = RequestMethod.GET)
	protected void list(final HttpServletRequest req,
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

	@RequestMapping(method = RequestMethod.POST)
	protected void contactRequest(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final String contactRequest = "/contactrequest/contactRequest.jsp";

		final ContactRequestService service = ServiceProvider
				.getContactRequestService();

		final PropertyService PropService = ServiceProvider
				.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		final String firstName = req.getParameter("first_name");
		final String lastName = req.getParameter("last_name");
		final String email = req.getParameter("email");
		final String telephone = req.getParameter("phone");
		final String description = req.getParameter("description");
		final Integer propID = Integer.valueOf(req.getParameter("property_id"));
		final Property property = PropService.getPropertyByID(propID, errors);

		final boolean valid = service.saveContactRequest(firstName, lastName,
				email, telephone, description, property, errors);

		if (valid) {
			final EmailService notification = ServiceProvider.getEmailService();
			notification.sendMail(property.getOwner(), property, errors,
					firstName, lastName, email, telephone, description);
			req.setAttribute("user", property.getOwner());
			req.setAttribute("property", property);
			HTMLUtils.render(contactRequest, req, resp);
		} else {
			req.setAttribute("property", property);
			req.setAttribute("errors", errors);
			HTMLUtils.render("/viewproperties/viewproperty.jsp", req, resp);
		}

	}

}
