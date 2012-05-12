package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;
import ar.edu.itba.it.paw.web.command.LoginForm;
import ar.edu.itba.it.paw.web.command.RegistrationForm;
import ar.edu.itba.it.paw.web.command.validator.RegistrationFormValidator;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {

	private RegistrationFormValidator registrationFormValidator;
	private HibernateUserRepository userRepository;

	@Autowired
	public UserController(
			final RegistrationFormValidator registrationFormValidator,
			final HibernateUserRepository userRepository) {
		this.registrationFormValidator = registrationFormValidator;
		this.userRepository = userRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView login(final LoginForm loginForm,
			final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		// TODO: Make this
		final String username = loginForm.getUser_username();
		final String password = loginForm.getUser_password();
		final String remember = loginForm.getRemember();

		final UserManager manager = (UserManager) request
				.getAttribute("userManager");

		final User user = this.userRepository.getByNameAndPassword(username,
				password);
		final boolean loginValid = user != null;

		if (loginValid) {
			final ModelAndView mav = new ModelAndView("forward:/index");
			final CookiesManager cookman = new CookiesManager(request, response);
			cookman.setUser(username, password, remember);
			manager.setCurrentUser(user);
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

		manager.setCurrentUser(null);

		final CookiesManager cookman = new CookiesManager(req, resp);
		if (cookman.getRemember().equals("session")) {
			cookman.resetUser();
		}
		return "redirect:/index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/register")
	protected ModelAndView registerGet(final RegistrationForm form)
			throws ServletException, IOException {

		final ModelAndView mav = new ModelAndView("user/register");
		mav.addObject("registerForm", form);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	protected ModelAndView registerPost(
			final RegistrationForm registrationForm,
			final Errors validationErrors) throws ServletException, IOException {

		this.registrationFormValidator.validate(registrationForm,
				validationErrors);

		final boolean valid = !validationErrors.hasErrors();

		if (valid) {
			final User user = registrationForm.build();
			this.userRepository.save(user);
			return new ModelAndView("redirect:/index");
		} else {
			return this.registerGet(registrationForm);
		}

	}

}
