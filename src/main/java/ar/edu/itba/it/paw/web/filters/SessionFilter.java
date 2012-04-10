package ar.edu.itba.it.paw.web.filters;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.SessionManager;
import ar.edu.itba.it.paw.web.session.UserManager;

public class SessionFilter implements Filter {

	private String excludePatterns;

	public void init(final FilterConfig filterConfig) throws ServletException {
		this.excludePatterns = filterConfig.getInitParameter("excludePatterns");
	}

	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		final UserManager userManager = new SessionManager(
				httpRequest.getSession());

		final CookiesManager manager = new CookiesManager(request, response);

		final String remember = manager.getRemember();
		if (remember != null) {
			if (remember.equals("session")) {
				final UserService service = ServiceProvider.getUserService();
				service.login(manager.getName(), manager.getPassword(),
						userManager);
			}
		}

		if (userManager.getCurrentUser() != null) {
			httpRequest.setAttribute("current_user",
					userManager.getCurrentUser());
		}

		httpRequest.setAttribute("userManager", userManager);
		httpResponse.setCharacterEncoding("UTF-8");

		// Assets and other pages are for anonymous users
		if (Pattern.matches(this.excludePatterns, httpRequest.getServletPath())) {
			chain.doFilter(request, response);
			return;
		}

		if (httpRequest.getServletPath().equals("/")
				|| httpRequest.getServletPath().equals("")) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/index");
			return;
		}

		if (userManager.getCurrentUser() != null) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath()
					+ "/register");
		}
	}

	public void destroy() {
		// nop
	}
}
