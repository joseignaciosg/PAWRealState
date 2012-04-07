package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

/**
 * Los usuarios registrados deben poder cargar y eliminar fotos asociadas a una
 * publicación. En la página de detalle de una publicación se deben poder ver
 * las fotos relacionadas a la misma
 * 
 * @author cris
 * 
 */

@SuppressWarnings("serial")
public class PhotoAddPage extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

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

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		try {
			if (ServletFileUpload.isMultipartContent(req)) {

				final Photo photo = this.buildPhoto(req);

				final PhotoService service = ServiceProvider.getPhotoService();

				service.savePhoto(photo.getData(), photo.getPropertyid(), null,
						new ArrayList<String>());

				resp.sendRedirect(req.getContextPath()
						+ "/myproperties/myphotos?propertyId="
						+ photo.getPropertyid());
			} else {
				resp.sendRedirect(req.getContextPath() + "/index");
			}
		} catch (final FileUploadException e) {
			e.printStackTrace();
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
		if (!found) {
			throw new FileUploadException("No file uploaded");
		}

		return new Photo(null, data, fileName.substring(fileName
				.lastIndexOf(".") + 1), propertyId);
	}

}
