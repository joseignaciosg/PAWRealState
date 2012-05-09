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
import org.springframework.validation.Errors;
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
import ar.edu.itba.it.paw.web.command.validator.PropertyFormValidator;
import ar.edu.itba.it.paw.web.command.validator.SearchFormValidator;
import ar.edu.itba.it.paw.web.session.UserManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/property")
public class PropertyController {

	private PropertyService propertyservice;
	private SearchFormValidator searchFormValidator;
	private PropertyFormValidator propertyFormValidatior;

	@Autowired
	public PropertyController(final PropertyService propertyservice,
			final SearchFormValidator searchFormValidator,
			final PropertyFormValidator propertyFormValidatior) {
		this.propertyservice = propertyservice;
		this.searchFormValidator = searchFormValidator;
		this.propertyFormValidatior = propertyFormValidatior;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	protected ModelAndView searchGET(@Valid final SearchForm searchForm,
			final Errors errors) throws ServletException, IOException {
		return this.searchPOST(searchForm, errors);
	}

	/*
	 * Searches and displays all the properties according to the request
	 * parameters
	 * 
	 * @param searchform: object with all the parameters for the search
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	protected ModelAndView searchPOST(final SearchForm searchForm,
			final Errors errors) throws ServletException, IOException {

		final SearchForm sessionSearchForm = searchForm;
		this.searchFormValidator.validate(sessionSearchForm, errors);

		final List<Property> props = this.propertyservice.advancedSearch(
				searchForm.getOperation(), searchForm.getType(),
				searchForm.getPricelow(), searchForm.getPricehigh(),
				searchForm.getPage(), 5, searchForm.getOrder());

		final ModelAndView mav = new ModelAndView("property/search");

		mav.addObject("props", props);
		mav.addObject("propertyForm", sessionSearchForm);
		return mav;
	}

	/*
	 * Displays the form whereby the user can create a new property
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected ModelAndView newGET(final ModelAndView mav,
			final PropertyForm propertyForm) throws ServletException,
			IOException {
		mav.setViewName("property/new");
		mav.addObject("propertyForm", propertyForm);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected ModelAndView newPOST(final PropertyForm propertyForm,
			final Errors validateErrors, final HttpServletRequest request)
			throws IOException, ServletException {
		final List<String> errors = new ArrayList<String>();
		boolean saved = false;
		this.propertyFormValidatior.validate(propertyForm, validateErrors);

		// TODO: Sacá todas las llamadas a los providers y meté autowire!
		final UserManager manager = (UserManager) request
				.getAttribute("userManager");

		final User user = manager.getCurrentUser();

		final List<String> services = new ArrayList<String>();

		saved = this.propertyservice.saveProperty(propertyForm.getOperation(),
				propertyForm.getType(), propertyForm.getNeighborhood(),
				propertyForm.getAddress(), propertyForm.getPrice(),
				propertyForm.getSpaces(), propertyForm.getCoveredArea(),
				propertyForm.getFreeArea(), propertyForm.getAge(), services,
				propertyForm.getDescription(), errors, user,
				propertyForm.getProperty());

		final ModelAndView mav = new ModelAndView();
		mav.addObject("propertyForm", propertyForm);

		if (saved) {
			mav.setViewName("redirect:/property/list");
			return mav;
		} else {
			return this.newGET(mav, propertyForm);
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

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	protected ModelAndView editGET(final HttpServletRequest req,
			final HttpServletResponse resp, final ModelAndView mav,
			@RequestParam(value = "id") final Property property) {
		mav.setViewName("property/edit");

		if (!mav.getModel().containsKey("propertyForm")) {
			mav.addObject("propertyForm", new PropertyForm(property));
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	protected ModelAndView editPOST(final HttpServletRequest req,
			final HttpServletResponse resp, final PropertyForm propertyForm)
			throws IOException, ServletException {
		final List<String> errors = new ArrayList<String>();
		final Property property = propertyForm.getProperty();
		boolean saved = false;
		final List<String> services = new ArrayList<String>();
		try {

			// TODO: Fix this
			saved = this.propertyservice.saveProperty(
					propertyForm.getOperation(), propertyForm.getType(),
					propertyForm.getNeighborhood(), propertyForm.getAddress(),
					propertyForm.getPrice(), propertyForm.getSpaces(),
					propertyForm.getCoveredArea(), propertyForm.getFreeArea(),
					propertyForm.getAge(), services,
					propertyForm.getDescription(), errors,
					propertyForm.getCurrentUser(), propertyForm.getProperty());
		} catch (final NumberFormatException e) {
			errors.add("Parámetros inválidos");
		}

		if (saved) {
			return new ModelAndView("redirect:/property/list");
		} else {

			final ModelAndView mav = new ModelAndView("/property/edit");

			mav.addObject("propertyForm", propertyForm);
			mav.addObject("errors", errors);
			mav.addObject("id", propertyForm.getProperty().getId().toString());

			return mav;
		}
	}

	/*
	 * Displays the detail of a property
	 * 
	 * @param id : id of the property to
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/view")
	protected ModelAndView view(
			@RequestParam(value = "id") final Property property)
			throws ServletException, IOException {
		final ModelAndView mav = new ModelAndView("property/view");
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
