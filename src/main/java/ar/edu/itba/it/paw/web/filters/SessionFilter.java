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

public class SessionFilter implements Filter {

	private String excludePatterns;

	public void init(final FilterConfig filterConfig) throws ServletException {
		this.excludePatterns = filterConfig.getInitParameter("excludePatterns");
	}

	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (Pattern.matches(this.excludePatterns, httpRequest.getServletPath())) {
			chain.doFilter(request, response);
			return;
		}

		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setCharacterEncoding("UTF-8");

		// TODO: Check session and make it work.

		chain.doFilter(request, response);

		// final Manager<User> userResolver = new
		// SessionUserManager(httpRequest);
		//
		// if ((userResolver.resolve() != null || httpRequest.getServletPath()
		// .equals("/index"))
		// && !userResolver.unlink()
		// && !httpRequest.getServletPath().equals("/")) {
		// chain.doFilter(request, response);
		// } else {
		// final HttpServletResponse httpResponse = (HttpServletResponse)
		// response;
		// httpResponse.sendRedirect(httpRequest.getContextPath() + "/index");
		// }
	}

	public void destroy() {
		// nop
	}
}
