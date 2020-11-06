package br.com.controleaula.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("home()...");
		ModelAndView mv = new ModelAndView("base-view");
		mv.addObject("content_data", "Content Data");
		mv.addObject("content_title", "Controle Aulas");
		return mv;
	}
	
}
