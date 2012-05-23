package ar.edu.itba.it.paw.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class NotFoundFilter implements Filter {

	final Logger log = Logger.getLogger(this.getClass());

	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain) {

		try {
			chain.doFilter(request, response);
		} catch (final Exception e) {
			try {
				final HttpServletResponse httpResponse = ((HttpServletResponse) response);
				httpResponse.sendRedirect(((HttpServletRequest) request)
						.getContextPath() + "/assets/404.html");
				httpResponse.setStatus(404);
			} catch (final IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}
