package ar.edu.itba.it.paw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.repositories.impl.HibernateAgencyRepository;
import ar.edu.itba.it.paw.web.command.SearchForm;

@Controller
@RequestMapping("/agency")
public class AgencyController {

	@Autowired
	private HibernateAgencyRepository agencyRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@SuppressWarnings("rawtypes")
	protected ModelAndView listGET(final ModelAndView mav,
			final SearchForm searchForm) {
		final List list = this.agencyRepository.getAllWithProp();
		mav.addObject("agencyList", list);
		mav.addObject("searchForm", searchForm);
		mav.setViewName("agency/list");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/list")
	protected ModelAndView listPOST(final SearchForm searchForm) {
		final ModelAndView mav = new ModelAndView("redirect:/property/search");
		mav.addObject("searchForm", searchForm);
		return mav;
	}

}
