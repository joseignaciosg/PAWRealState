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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.web.command.PropertyForm;
import ar.edu.itba.it.paw.web.command.SearchForm;
import ar.edu.itba.it.paw.web.command.validator.SearchFormValidator;
import ar.edu.itba.it.paw.web.session.UserManager;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/property")
public class PropertyController {

	private HibernatePropertyRepository propertyRepository;

	private SearchFormValidator searchFormValidator;

	@Autowired
	public PropertyController(
			final HibernatePropertyRepository propertyRepository,
			final SearchFormValidator searchFormValidator) {
		this.propertyRepository = propertyRepository;
		this.searchFormValidator = searchFormValidator;
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
	protected ModelAndView searchPOST(@Valid final SearchForm searchForm,
			final Errors errors) throws ServletException, IOException {

		final SearchForm sessionSearchForm = searchForm;
		this.searchFormValidator.validate(sessionSearchForm, errors);

		final List<Property> props = serv.advancedSearch(
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
	protected ModelAndView newPOST(@Valid final PropertyForm propertyForm,
			final BindingResult validateErrors, final HttpServletRequest request)
			throws IOException, ServletException {
		boolean saved = false;
		if (!validateErrors.hasErrors()) {
			// TODO: Security check
			final Property built = propertyForm.build();
			this.propertyRepository.save(built);
			saved = true;
		}
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
			final HttpServletResponse resp,
			@RequestParam("ID") final Property property)
			throws ServletException, IOException {
		// TODO: Security check
		this.propertyRepository.delete(property);

		return "redirect:/property/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String changeVisibility(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam("id") final Property property) throws IOException {

		property.toggleVisibility();

		this.propertyRepository.save(property);

		return "redirect:/property/list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	protected ModelAndView editGET(final ModelAndView mav,
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

		boolean saved = false;
		if (!validateErrors.hasErrors()) {
			// TODO: Security check
			final Property built = propertyForm.build();
			this.propertyRepository.save(built);
			saved = true;
		}
		final ModelAndView mav = new ModelAndView();
		mav.addObject("propertyForm", propertyForm);

		if (saved) {
			mav.setViewName("redirect:/property/list");
			return mav;
		} else {
			return this.editGET(mav, propertyForm.getProperty());
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
