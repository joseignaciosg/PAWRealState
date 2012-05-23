package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.web.command.PropertyPhotoForm;
import ar.edu.itba.it.paw.web.command.validator.PropertyPhotoFormValidator;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	PropertyPhotoFormValidator validator;
	HibernatePropertyRepository propertyRepository;

	@Autowired
	public PhotoController(final PropertyPhotoFormValidator validator,
			final HibernatePropertyRepository propertyRepository) {
		this.validator = validator;
		this.propertyRepository = propertyRepository;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected ModelAndView addGET(
			@RequestParam("propertyId") final Property property,
			final ModelAndView mav, final PropertyPhotoForm propertyphotoform) {
		mav.setViewName("photo/new");
		mav.addObject("property", property);
		mav.addObject("photoForm", propertyphotoform);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected ModelAndView addPOST(final PropertyPhotoForm propertyphotoform,
			final Errors errors, final ModelAndView mav) {
		this.validator.validate(propertyphotoform, errors);
		final boolean error = errors.hasErrors();
		if (error) {
			mav.addObject("propertyId", propertyphotoform.getProperty().getId());
			return this.addGET(propertyphotoform.getProperty(), mav,
					propertyphotoform);
		}
		try {
			propertyphotoform.getProperty().addPhoto(
					new Photo(propertyphotoform.getFile().getBytes(), "jpeg",
							propertyphotoform.getProperty()));
		} catch (final IOException e) {
			final Logger log = Logger.getLogger("ERROR");
			log.debug("Error adding photo");
		}

		mav.setViewName("redirect:/photo/list");
		mav.addObject("propertyId", propertyphotoform.getProperty().getId()
				.toString());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String delete(@RequestParam(value = "photoId") final Photo photo) {

		final Property prop = photo.getProperty();
		prop.removePhoto(photo);

		return "redirect:/photo/list?propertyId=" + prop.getId();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView list(
			@RequestParam(value = "propertyId") final Property property) {

		final ModelAndView mav = new ModelAndView("photo/list");
		mav.addObject("property", property);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected void view(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam(value = "ID") final Photo p) throws IOException {

		if (p == null) {
			resp.setStatus(404);
			return;
		}

		resp.setHeader("Content-Type", "image/jpeg");
		resp.getOutputStream().write(p.getData());
	}

}
