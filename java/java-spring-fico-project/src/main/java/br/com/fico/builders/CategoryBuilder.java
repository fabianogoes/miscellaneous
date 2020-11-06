package br.com.fico.builders;

import br.com.fico.models.Category;

public class CategoryBuilder {

	private Category category = new Category(null, "Test "+Math.random());
	
	public Category build(){
		return this.category;
	}
	
	public CategoryBuilder withId(Long id){
		this.category.setId(id);
		return this;
	}
	
	public CategoryBuilder withName(String name){
		this.category.setName(name);
		return this;
	}
	
}
