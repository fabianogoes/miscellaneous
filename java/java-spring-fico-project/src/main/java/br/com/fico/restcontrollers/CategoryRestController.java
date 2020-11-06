package br.com.fico.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fico.models.Category;
import br.com.fico.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping
	public List<Category> get() {
		return categoryService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Category create(@RequestBody Category category) {
		System.out.println( "post( "+category+" )" );
		return categoryService.save(category);
	}

}
