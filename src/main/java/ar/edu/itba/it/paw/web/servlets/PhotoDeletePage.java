package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.web.utils.HTMLUtils;

public class PhotoDeletePage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5507236168764827590L;

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final PhotoService service = ServiceProvider.getPhotoService();

		final List<String> errors = Collections.emptyList();

		final User currentUser = (User) req.getAttribute("current_user");

		service.deletePhoto(Integer.valueOf(req.getParameter("photoId")),
				currentUser, errors);

		HTMLUtils.redirectBack(req, resp);
	}
}
