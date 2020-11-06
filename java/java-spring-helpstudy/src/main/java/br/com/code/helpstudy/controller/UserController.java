package br.com.code.helpstudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.code.helpstudy.model.User;
import br.com.code.helpstudy.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	@Qualifier("userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return this.userService;
	}
	
	@RequestMapping(value = "/userView", method = RequestMethod.GET)
	public ModelAndView userView() {
		logger.info("userView()...");
		ModelAndView mv = new ModelAndView("user");
		mv.addObject("list", getUserService().getAll());
		return mv;
	}

	@RequestMapping(value = "/userPersist", method = RequestMethod.GET)
	public ModelAndView userPersist(User user) {
		logger.info("userPersist("+user+")...");
		getUserService().persist(user);
		return userView();
	}
	
	

}
