package ar.edu.itba.it.paw.web.interceptors;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.SessionManager;
import ar.edu.itba.it.paw.web.session.UserManager;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	HibernateUserRepository repository;

	private String filter = "(.*/assets/.*|.*/search(/)?|.*/contactrequest(/)?|.*/properties/view(/)?$|.*/index(/)?|.*/register(/)?|.*/login(/)?|.*/photos.*(/)?)";

	@Override
	public boolean preHandle(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler)
			throws Exception {
		final UserManager userManager = new SessionManager(this.repository,
				request.getSession());

		final CookiesManager manager = new CookiesManager(request, response);

		final String remember = manager.getRemember();
		if (remember != null) {
			if (remember.equals("session")) {
				final String name = manager.getName();
				final String password = manager.getPassword();

				final User u = this.repository.getByNameAndPassword(name,
						password);

				userManager.setCurrentUser(u);
			}
		}

		if (userManager.getCurrentUser() != null) {
			request.setAttribute("current_user", userManager.getCurrentUser());
		}

		request.setAttribute("userManager", userManager);

		if (userManager.getCurrentUser() == null
				&& !Pattern.matches(this.filter, request.getRequestURI())) {
			response.sendRedirect(request.getContextPath() + "/bin"
					+ "/user/register");
		}

		return true;
	}
}
