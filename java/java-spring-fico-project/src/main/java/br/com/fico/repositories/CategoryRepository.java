package br.com.fico.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fico.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
