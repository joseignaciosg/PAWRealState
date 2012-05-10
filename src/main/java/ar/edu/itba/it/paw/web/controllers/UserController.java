package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

		final boolean loginValid = true;

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

		if (true) {
			return new ModelAndView("redirect:/index");
		} else {
			return this.registerGet(form, req, resp);
		}

	}

}
