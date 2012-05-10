package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.services.ServiceProvider;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.command.LoginForm;
import ar.edu.itba.it.paw.web.command.RegistrationForm;
import ar.edu.itba.it.paw.web.command.validator.RegistrationFormValidator;
import ar.edu.itba.it.paw.web.cookies.CookiesManager;
import ar.edu.itba.it.paw.web.session.UserManager;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private RegistrationFormValidator registrationFormValidator;

	@Autowired
	public UserController(final UserService userService,
			final RegistrationFormValidator registrationFormValidator) {
		this.userService = userService;
		this.registrationFormValidator = registrationFormValidator;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView login(final LoginForm loginForm,
			final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		final String username = loginForm.getUser_username();
		final String password = loginForm.getUser_password();
		final String remember = loginForm.getRemember();

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

		final List<String> errors = new ArrayList<String>();
		this.registrationFormValidator.validate(registrationForm,
				validationErrors);
		final boolean valid = this.userService.register(
				registrationForm.getFirstName(),
				registrationForm.getLastName(), registrationForm.getEmail(),
				registrationForm.getPhone(), registrationForm.getUserName(),
				registrationForm.getPassword(),
				registrationForm.getRepeatedPassword(), errors);

		if (valid) {
			return new ModelAndView("redirect:/index");
		} else {
			return this.registerGet(registrationForm);
		}

	}

}
