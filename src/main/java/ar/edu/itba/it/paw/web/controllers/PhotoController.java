package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@RequestMapping(method = RequestMethod.GET, value = "/addphoto")
	protected void addGET(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException,
			ServletException {
		final PropertyService service = ServiceProvider.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		try {
			final Property property = service.getPropertyByID(
					Integer.valueOf(req.getParameter("propertyId")), errors);

			req.setAttribute("property", property);

		} catch (final Exception ex) {
			errors.add("Parámetros inválidos");
		}

		if (errors.size() > 0) {
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			HTMLUtils.render("myproperties/newphoto.jsp", req, resp);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addphoto")
	protected void addPOST(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException {
		final List<String> errors = new ArrayList<String>();
		try {

			if (ServletFileUpload.isMultipartContent(req)) {

				final Photo photo = this.buildPhoto(req);

				final PhotoService service = ServiceProvider.getPhotoService();

				final User currentUser = (User) req
						.getAttribute("current_user");

				service.savePhoto(photo.getData(), photo.getPropertyId(),
						currentUser, new ArrayList<String>());

				resp.sendRedirect(req.getContextPath()
						+ "/myproperties/myphotos?propertyId="
						+ photo.getPropertyId());
				return;
			} else {
				errors.add("Archivo inválido");
			}
		} catch (final FileUploadException e) {
			errors.add("Archivo inválido");
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
	protected void delete(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException {
		final PhotoService service = ServiceProvider.getPhotoService();

		final List<String> errors = Collections.emptyList();

		final User currentUser = (User) req.getAttribute("current_user");

		service.deletePhoto(Integer.valueOf(req.getParameter("photoId")),
				currentUser, errors);

		HTMLUtils.redirectBack(req, resp);
	}

	@RequestMapping(method = RequestMethod.GET)
	protected void list(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException,
			ServletException {
		final PropertyService service = ServiceProvider.getPropertyService();

		final List<String> errors = new ArrayList<String>();

		final Property property = service.getPropertyByID(
				Integer.valueOf(req.getParameter("propertyId")), errors);

		req.setAttribute("property", property);

		if (errors.size() > 0) {
			resp.sendRedirect(req.getContextPath() + "/index");
		} else {
			HTMLUtils.render("myproperties/myphotos.jsp", req, resp);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	protected void show(final HttpServletRequest req,
			final HttpServletResponse resp) throws IOException {
		final PhotoService service = ServiceProvider.getPhotoService();
		final List<String> errors = new ArrayList<String>();

		final Photo p = service.getPhotoById(
				Integer.valueOf(req.getParameter("ID")), errors);

		if (p == null) {
			resp.setStatus(404);
			return;
		}

		resp.setHeader("Content-Type", "image/jpeg");
		resp.getOutputStream().write(p.getData());

	}

}
