package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	PhotoService photoService;

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
			final HttpServletResponse response) {

		// TODO: Validar tipo de archivo
		try {
			this.photoService.savePhoto(file.getBytes(), property.getId(),
					property.getOwner(), new ArrayList<String>());
		} catch (final IOException e) {
			// TODO: Log this
		}

		return "redirect:/photo/list?propertyId=" + property.getId().toString();
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String delete(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam(value = "photoId") final Photo photo)
			throws IOException {

		final UserManager manager = (UserManager) req
				.getAttribute("userManager");

		// Eliminar la foto
		this.photoService.deletePhoto(photo.getId(), manager.getCurrentUser(),
				new ArrayList<String>());

		return "redirect:/photo/list?propertyId=" + photo.getPropertyId();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView list(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam(value = "propertyId") final Property property)
			throws IOException, ServletException {
		final ModelAndView mav = new ModelAndView("photo/list");

		mav.addObject("property", property);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected void show(final HttpServletRequest req,
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
