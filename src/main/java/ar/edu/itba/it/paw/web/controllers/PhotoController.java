package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	PhotoService photoService;

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	protected String addGET(@RequestParam("propertyId") final Property property) {
		return "photo/new";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/new")
	protected void addPOST(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam(value = "propertyId") final Property property)
			throws IOException {
		try {
			if (ServletFileUpload.isMultipartContent(req)) {

				final Photo photo = this.buildPhoto(req);

				final User currentUser = (User) req
						.getAttribute("current_user");

				this.photoService.savePhoto(photo.getData(),
						photo.getPropertyId(), currentUser,
						new ArrayList<String>());

				resp.sendRedirect(req.getContextPath()
						+ "/myproperties/myphotos?propertyId="
						+ photo.getPropertyId());
				return;
			} else {
				// errors.add("Archivo inválido");
			}
		} catch (final FileUploadException e) {
			// errors.add("Archivo inválido");
		}
	}

	@SuppressWarnings("unchecked")
	private Photo buildPhoto(final HttpServletRequest req)
			throws FileUploadException {
		byte[] data = null;
		String fileName = null;
		int propertyId = 0;
		final ServletFileUpload uploader = new ServletFileUpload(
				new DiskFileItemFactory());

		final List<FileItem> items = uploader.parseRequest(req);

		boolean found = false;
		if (items.size() != 2) {
			throw new FileUploadException("Invalid params upload");
		}
		for (final FileItem fileItem : items) {
			if (!fileItem.isFormField()) {
				data = fileItem.get();
				found = true;
				fileName = fileItem.getName();

			} else {
				propertyId = Integer.valueOf(fileItem.getString());
			}
		}
		if (!found || data.length == 0
				|| (!fileName.endsWith("jpg") && !fileName.endsWith("jpeg"))) {
			throw new FileUploadException("No file uploaded");
		}

		return new Photo(null, data, fileName.substring(fileName
				.lastIndexOf(".") + 1), propertyId);
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String delete(final HttpServletRequest req,
			final HttpServletResponse resp,
			@RequestParam(value = "photoId") final Photo photo)
			throws IOException {

		final UserManager manager = (UserManager) req
				.getAttribute("userManager");

		// Eliminar la foto
		// service.deletePhoto(Integer.valueOf(req.getParameter("photoId")),
		// currentUser, errors);

		return "/index"; // TODO: Volver al listado de fotos
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
