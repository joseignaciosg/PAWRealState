package ar.edu.itba.it.paw.web.filter;

import java.util.logging.LogRecord;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class ErrorFilter implements Filter {

	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain) {

		try {
			chain.doFilter(request, response);
		} catch (final Exception e) {
		}

	}

	public boolean isLoggable(final LogRecord record) {
		// TODO Auto-generated method stub
		return false;
	}

	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
