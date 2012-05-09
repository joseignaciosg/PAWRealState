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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.services.ServiceProvider;
import ar.edu.itba.it.paw.services.UserService;
import ar.edu.itba.it.paw.web.command.RegistrationForm;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView login(final HttpServletRequest req,
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
			final ModelAndView mav = new ModelAndView("forward:/index");
			final CookiesManager cookman = new CookiesManager(req, resp);
			cookman.setUser(username, password, remember);
			mav.addObject("errors", new String[] { "Bienvenido " + username });
			return mav;
		} else {
			final ModelAndView mav = new ModelAndView("forward:/index");
			mav.addObject("errors", new String[] { "Login inválido" });
			return mav;
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
		return "redirect:/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	protected ModelAndView registerGet(final RegistrationForm form,
			final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {

		final ModelAndView mav = new ModelAndView("user/register");

		mav.addObject("registerForm", form);

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	protected ModelAndView registerPost(final RegistrationForm form,
			final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println(form);

		final UserService service = ServiceProvider.getUserService();

		final List<String> errors = new ArrayList<String>();

		final boolean valid = service.register(form.getFirstName(),
				form.getLastName(), form.getEmail(), form.getPhone(),
				form.getUserName(), form.getPassword(),
				form.getRepeatedPassword(), errors);

		if (valid) {
			return new ModelAndView("redirect:/index");
		} else {
			req.setAttribute("errors", errors);
			return this.registerGet(form, req, resp);
		}

	}

}
