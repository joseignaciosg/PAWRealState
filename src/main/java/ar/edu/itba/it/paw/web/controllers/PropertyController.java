package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;
import ar.edu.itba.it.paw.web.command.ContactRequestForm;
import ar.edu.itba.it.paw.web.command.PropertyForm;
import ar.edu.itba.it.paw.web.command.SearchForm;
import ar.edu.itba.it.paw.web.command.validator.ContactRequestFormValidator;
import ar.edu.itba.it.paw.web.command.validator.PropertyFormValidator;
import ar.edu.itba.it.paw.web.command.validator.SearchFormValidator;

@Controller
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private HibernatePropertyRepository propertyRepository;
	@Autowired
	private HibernateUserRepository userRepository;
	@Autowired
	private ContactRequestFormValidator contactRequestFormValidator;
	@Autowired
	private SearchFormValidator searchFormValidator;
	@Autowired
	private PropertyFormValidator propertyFormValidator;

	@RequestMapping(method = RequestMethod.GET, value = "/search")
	protected ModelAndView searchGET(final SearchForm searchForm,
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
		this.searchFormValidator.validate(searchForm, errors);

		final boolean valid = !errors.hasErrors();

		final List<Property> props;
		if (valid) {
			props = this.propertyRepository.getAll(searchForm.build());
		} else {
			props = new ArrayList<Property>();
		}

		final ModelAndView mav = new ModelAndView("property/search");

		mav.addObject("props", props);
		mav.addObject("propertyServices", Property.getAllServices());
		mav.addObject("roomTypes", Property.getAllRoomTypes());
		mav.addObject("realStateAgencies", this.userRepository.getAllAgencies());
		mav.addObject("propertyForm", searchForm);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/contactrequest")
	// @ModelAttribute("contactRequestForm")
	protected ModelAndView contactRequestGET(
			@Valid final ContactRequestForm contactRequestForm,
			final Errors errors) throws ServletException, IOException {
		return this.contactRequestPOST(contactRequestForm, errors);
	}

	/*
	 * Searches and displays all the properties according to the request
	 * parameters
	 * 
	 * @param searchform: object with all the parameters for the search
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/contactrequest")
	// @ModelAttribute("contactRequestForm")
	protected ModelAndView contactRequestPOST(
			final ContactRequestForm contactRequestForm, final Errors errors)
			throws ServletException, IOException {

		this.contactRequestFormValidator.validate(contactRequestForm, errors);

		if (errors.hasErrors()) {
			final ModelAndView mav = new ModelAndView("/property/view");
			final ContactRequest request = contactRequestForm.build();
			mav.addObject("id", contactRequestForm.getProperty().getId());
			mav.addObject("user", request.getPropRefered().getOwner());
			mav.addObject("property", request.getPropRefered());
			mav.addObject("contactForm", contactRequestForm);
			return mav;
		} else {
			final ContactRequest request = contactRequestForm.build();
			this.propertyRepository.sendContactRequest(request);
			final ModelAndView mav = new ModelAndView("property/contactrequest");
			mav.addObject("user", request.getPropRefered().getOwner());
			mav.addObject("property", request.getPropRefered());
			mav.addObject("contactForm", contactRequestForm);
			return mav;
		}
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
		mav.addObject("propertyServices", Property.getAllServices());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected ModelAndView newPOST(final PropertyForm propertyForm,
			final Errors validateErrors) throws ServletException, IOException {

		this.propertyFormValidator.validate(propertyForm, validateErrors);

		boolean saved = false;
		if (!validateErrors.hasErrors()) {
			this.propertyRepository.save(propertyForm.build());
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
	protected String delete(@RequestParam("id") final Property property) {
		// TODO: Security check
		this.propertyRepository.delete(property);
		return "redirect:/property/list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/changevisibility")
	protected String changeVisibility(
			@RequestParam("id") final Property property) {
		property.toggleVisibility();

		this.propertyRepository.save(property);

		return "redirect:/property/list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reserve")
	protected String reserve(@RequestParam("id") final Property property) {
		property.reserve();
		this.propertyRepository.save(property);
		return "redirect:/property/list";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unreserve")
	protected String unreserve(@RequestParam("id") final Property property) {
		property.unreserve();
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
		if (!mav.getModel().containsKey("propertyServices")) {
			mav.addObject("propertyServices", Property.getAllServices());
		}

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	protected ModelAndView editPOST(final PropertyForm propertyForm,
			final Errors errors) throws IOException, ServletException {
		boolean saved = false;
		this.propertyFormValidator.validate(propertyForm, errors);
		if (!errors.hasErrors()) {
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
		property.updateVisitCount();

		final ModelAndView mav = new ModelAndView("property/view");
		mav.addObject("property", property);
		if (!mav.getModel().containsKey("contactRequestForm")) {
			mav.getModel().put("contactRequestForm", new ContactRequestForm());
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String list() throws ServletException, IOException {
		return "property/list";
	}

}
