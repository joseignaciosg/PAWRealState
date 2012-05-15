package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	HibernatePropertyRepository propertyRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected ModelAndView addGET(
			@RequestParam("propertyId") final Property property,
			final ModelAndView mav) {
		mav.setViewName("photo/new");
		mav.addObject("property", property);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected String addPOST(
			@RequestParam(value = "file") final MultipartFile file,
			@RequestParam(value = "propertyId") final Property property,
			final Object command, final Errors errors) {
		final boolean error = errors.hasErrors();
		if (error) {
			return "redirect:/photo/new?propertyId="
					+ property.getId().toString();
		}
		try {
			property.addPhoto(new Photo(file.getBytes(), "jpeg", property));
		} catch (final IOException e) {
			// TODO: Log this
		}

		return "redirect:/photo/list?propertyId=" + property.getId().toString();
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
