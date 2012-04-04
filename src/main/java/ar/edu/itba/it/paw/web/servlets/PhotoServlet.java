package ar.edu.itba.it.paw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.services.PhotoService;
import ar.edu.itba.it.paw.model.services.ServiceProvider;

public class PhotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1979136120814037552L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		try {
			final PhotoService service = ServiceProvider.getPhotoService();
			final List<String> errors = new ArrayList<String>();

			final Photo p = service.getPhotoById(
					Integer.valueOf(req.getParameter("ID")), errors);

		} catch (final Exception e) {

		}

	}
}
