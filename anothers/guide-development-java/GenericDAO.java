package com.eprogramar.springjpa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
Para lidarmos com as transformações de exceções específicas de plataforma para as da hierarquia uniforme do
Spring. A solução é bastante simples: basta anotarmos nosso DAO com @Repository ao invés de @Component 
Esta é uma solução pode ser aplicada a qualquer DAO, inclusive JDBC. 
Esta anotação é um estereótipo que permite ao container do Spring aplicar funcionalidades mais interessantes a DAOs. 
-----------------------
Apenas a presença da anotação @Repositoty nas classes DAO não é suficiente, 
faz-se necessário também incluir a definição de um novo bean do tipo PersistenceExceptionTranslationPostProcessor. 
O que este bean faz é criar um aspecto do tipo after throw para todos os beans anotados com @Repository.
A exceção será interceptada e automaticamente convertida para outra presente na hierarquia de exceções 
uniformizada do Spring. 
adicione o bean no arquivo: servlet-context.xml
<bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>	
*/
@Repository
public class GenericDAO<T> {
	private static final Logger logger = LoggerFactory.getLogger(GenericDAO.class);
	
	@PersistenceContext
	private EntityManager manager;

	public EntityManager getManager(){
		return this.manager;
	}
	
	@Transactional
	public void persist(T entity) {
		logger.info("persist("+entity+")");
		this.getManager().persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> clazz){
		logger.info("getAll("+clazz.getName()+")");
		return this.getManager().createQuery("from "+clazz.getName()).getResultList();
	}
	
	public T findById(Class<T> clazz, Long id){
		logger.info("findById("+clazz.getName()+","+id+")");
		return this.getManager().find(clazz, id);
	}
}
