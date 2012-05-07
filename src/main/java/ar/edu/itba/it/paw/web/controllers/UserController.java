package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(method = RequestMethod.POST)
	protected String login(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final String username = req.getParameter("user_username");
		final String password = req.getParameter("user_password");
		final String remember = req.getParameter("remember");

		final UserManager manager = (UserManager) req
				.getAttribute("userManager");

		final UserService service = ServiceProvider.getUserService();

		final boolean loginValid = service.login(username, password, manager);

		if (loginValid) {
			final CookiesManager cookman = new CookiesManager(req, resp);
			cookman.setUser(username, password, remember);
			return "redirect:/index";
		} else {
			final List<String> errors = new ArrayList<String>();

			errors.add("Usuario/Contraseña inválidos");

			req.setAttribute("errors", errors);
			return "redirect:/index";
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	protected String logout(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final UserManager manager = (UserManager) req
				.getAttribute("userManager");

		final CookiesManager cookman = new CookiesManager(req, resp);
		if (cookman.getRemember().equals("session")) {
			cookman.resetUser();
		}
		final UserService service = ServiceProvider.getUserService();

		service.logout(manager);
		return "register:..";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	protected String registerGet(final User user, final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		return "register/register";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	protected String registerPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final UserService service = ServiceProvider.getUserService();

		final List<String> errors = new ArrayList<String>();

		final boolean valid = service.register(
				req.getParameter("user_first_name"),
				req.getParameter("user_last_name"),
				req.getParameter("user_email"), req.getParameter("user_phone"),
				req.getParameter("user_username"),
				req.getParameter("user_password"),
				req.getParameter("user_password_repeated"), errors);

		if (valid) {
			return "redirect:/index";
		} else {
			req.setAttribute("errors", errors);
			return "redirect:register";
		}

	}

}
