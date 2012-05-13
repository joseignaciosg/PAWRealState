package ar.edu.itba.it.paw.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.repositories.impl.HibernateAgencyRepository;

@Controller
@RequestMapping("/agency")
public class AgencyController {

	@Autowired
	private HibernateAgencyRepository agencyRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	protected ModelAndView list(final ModelAndView mav) {
		mav.addObject("agencyList", this.agencyRepository.getAllWithProp());
		mav.setViewName("agency/list");
		return mav;
	}

}
