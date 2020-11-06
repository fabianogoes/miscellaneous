package br.com.controleaula.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController<T> {
	
	private final String BASE_VIEW = "base-view";
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	private Class<T> clazz;
	public void setClazz(Class<T> clazz){
		this.clazz = clazz;
	}
	
	private ModelAndView getModelAndViewBase(){
		ModelAndView mv = new ModelAndView(BASE_VIEW);
		mv.addObject("content_data", clazz.getSimpleName().toLowerCase()+".jsp");
		mv.addObject("content_title", clazz.getSimpleName());
		mv.addObject("request_mapping_add", "/"+clazz.getSimpleName().toLowerCase()+"/add");
		mv.addObject("model_name", clazz.getSimpleName());
		return mv;
	}
	
	//@Autowired
	protected BaseService<T> service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		logger.info("["+clazz.getName()+"]list()...");
		ModelAndView mv = this.getModelAndViewBase();		
		mv.addObject("list", this.service.listAll(clazz));
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		logger.info("["+clazz.getName()+"]form()...");
		ModelAndView mv = this.getModelAndViewBase();
		@SuppressWarnings("unchecked")
		T model = (T)Class.forName( clazz.getName() ).newInstance();
		mv.addObject("model", model);
		return mv;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id) {
		logger.info("["+clazz.getName()+"]edit()...");
		ModelAndView mv = this.getModelAndViewBase();
		mv.addObject("model", this.service.findById(clazz, id));
		return mv;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(T entity) {
		logger.info("["+clazz.getName()+"]list()...");
		this.service.save(entity);
		return "redirect:/"+clazz.getSimpleName().toLowerCase()+"/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		logger.info("["+clazz.getName()+"]list()...");
		this.service.delete(clazz, id);
		return "redirect:/"+clazz.getSimpleName().toLowerCase()+"/list";
	}
	
}
