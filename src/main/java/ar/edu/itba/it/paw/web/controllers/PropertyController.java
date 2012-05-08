package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.ContactRequestService;
import ar.edu.itba.it.paw.model.services.EmailService;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.command.PropertyForm;
import ar.edu.itba.it.paw.web.command.SearchForm;
import ar.edu.itba.it.paw.web.command.validator.SearchFormValidator;
import ar.edu.itba.it.paw.web.session.UserManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private SearchFormValidator searchFormValidator;
	private final PropertyService propertyservice;

	@Autowired
	public PropertyController(final PropertyService propertyservice) {
		this.propertyservice = propertyservice;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	protected ModelAndView searchGET(@Valid final SearchForm searchForm,
			final BindingResult validateErrors) throws ServletException,
			IOException {
		return this.searchPOST(searchForm, validateErrors);
	}

	/*
	 * Searches and displays all the properties according to the request
	 * parameters
	 * 
	 * @param searchform: object with all the parameters for the search
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	protected ModelAndView searchPOST(@Valid SearchForm searchForm,
			final BindingResult validateErrors) throws ServletException,
			IOException {

		final SearchForm sessionSearchForm = searchForm;
		final List<String> errorStrings = new ArrayList<String>();
		if (validateErrors.hasErrors()) {

			final List<FieldError> errors = validateErrors.getFieldErrors();
			for (final FieldError objectError : errors) {
				// TODO: Resolver mensajes de error por acá con alguna clase
				// auxiliar.
				errorStrings.add(objectError.getObjectName() + ","
						+ objectError.getField());
			}
		}
		System.out.println(searchForm);

		if (searchForm == null || validateErrors.hasErrors()) {
			searchForm = new SearchForm();
		}

		final PropertyService serv = ServiceProvider.getPropertyService();
		final List<Property> props = serv.advancedSearch(
				searchForm.getOperation(), searchForm.getType(),
				searchForm.getPricelow(), searchForm.getPricehigh(),
				searchForm.getPage(), 5, searchForm.getOrder());

		final ModelAndView mav = new ModelAndView("property/search");

		if (errorStrings.size() > 0) {
			mav.addObject("errors", errorStrings);
		}
		mav.addObject("props", props);
		mav.addObject("propertyForm", sessionSearchForm);
		return mav;
	}

	/*
	 * Displays the form whereby the user can create a new property
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected ModelAndView newGET(final PropertyForm propertyForm)
			throws ServletException, IOException {
		final ModelAndView mav = new ModelAndView("property/new");
		mav.addObject("propertyForm", propertyForm);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected ModelAndView newPOST(final PropertyForm propertyForm)
			throws IOException, ServletException {
		final List<String> errors = new ArrayList<String>();

		final PropertyService service = ServiceProvider.getPropertyService();
		final User currentUser = propertyForm.getCurrentUser();
		boolean saved = false;
		final List<String> services = new ArrayList<String>();
		// if (propertyForm.getService().isCable()) {
		// services.add("cable");
		// }
		// if (propertyForm.getService().isLobby()) {
		// services.add("salon");
		// }
		// if (propertyForm.getService().isPaddle()) {
		// services.add("paddle");
		// }
		// if (propertyForm.getService().isSwimmingpool()) {
		// services.add("swimmingpool");
		// }
		// if (propertyForm.getService().isQuincho()) {
		// services.add("quincho");
		// }
		// if (propertyForm.getService().isTelephone()) {
		// services.add("telephone");
		// }

		try {
			saved = service.saveProperty(propertyForm.getOperation(),
					propertyForm.getType(), propertyForm.getNeighborhood(),
					propertyForm.getAddress(), propertyForm.getPrice(),
					propertyForm.getSpaces(), propertyForm.getCoveredArea(),
					propertyForm.getFreeArea(), propertyForm.getAge(),
					services, propertyForm.getDescription(), errors,
					currentUser);
		} catch (final NumberFormatException e) {
			errors.add("Parámetros inválidos");
		}
		final ModelAndView mav = new ModelAndView();
		mav.addObject(errors);
		mav.addObject("propertyForm", propertyForm);

		if (saved) {
			mav.setViewName("redirect:/property/list");
			return mav;
		} else {
			return this.newGET(propertyForm);
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
			return "redirect:/index";
		} else {
			// HTMLUtils.redirectBack(req, resp);
			return "redirect:/index";
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
		final List<String> services = new ArrayList<String>();
		// new Services(req.getParameter("property_cable") != null,
		// req.getParameter("property_telephone") != null,
		// req.getParameter("property_swimmingpool") != null,
		// req.getParameter("property_lobby") != null, req
		// .getParameter("property_paddle") != null,
		// req.getParameter("property_quincho") != null), req
		// .getParameter("property_description"),
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
					services, req.getParameter("property_description"), errors,
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

	/*
	 * Displays the detail of a property
	 * 
	 * @param id : id of the property to
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/view")
	protected ModelAndView view(@RequestParam("id") final int id)
			throws ServletException, IOException {
		final List<String> errors = new ArrayList<String>();
		final PropertyService propservice = ServiceProvider
				.getPropertyService();

		final Property property = propservice.getPropertyByID(id, errors);

		final ModelAndView mav = new ModelAndView("property/view");
		mav.addObject("errors", errors);
		mav.addObject("property", property);
		return mav;

	}

	@RequestMapping(method = RequestMethod.GET)
	protected String list(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final UserManager userManager = (UserManager) req
				.getAttribute("userManager");
		final UserService service = ServiceProvider.getUserService();

		req.removeAttribute("current_user");
		req.setAttribute("current_user",
				service.getById(userManager.getCurrentUser().getId()));

		return "property/list";
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
