package br.com.fico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fico.models.Category;
import br.com.fico.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category save(Category category) {
		try{
			return this.categoryRepository.save(category);
		}catch(Exception ex){
			System.out.println( ex.getMessage() );
			if( ex.getMessage().contains( "constraint [UQ_CATEGORY_NAME]" ) ){
				throw new RuntimeException( "Nome de Categoria ["+category.getName()+" ] já existe!" );
			}
		}
		return null;
	}

	public List<Category> findAll() {
		return (List<Category>) this.categoryRepository.findAll();
	}

}
