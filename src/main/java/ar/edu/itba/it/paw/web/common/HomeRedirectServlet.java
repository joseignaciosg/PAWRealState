package ar.edu.itba.it.paw.web.common;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class HomeRedirectServlet extends HttpServlet {
	private static final String HOME_URL_PARAM = "homeUrl";

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final String target = this.getInitParameter(HOME_URL_PARAM);
		if (target == null) {
			throw new UnavailableException(
					"No home has been defined. Set property");
		}
		resp.sendRedirect(target);
	}
}
