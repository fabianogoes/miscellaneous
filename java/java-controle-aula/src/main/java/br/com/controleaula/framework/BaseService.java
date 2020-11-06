package br.com.controleaula.framework;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T> {

	public static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	
	@Autowired
	protected BaseDAO<T> dao;
	
	public List<T> listAll(Class<T> clazz){
		logger.info("listAll()...");
		return this.dao.listAll(clazz);
	}
	
	public T insert(T entity){
		logger.info("insert( "+entity+" )...");
		return this.dao.insert( entity );
	}
	
	public T update(T entity){
		logger.info("update( "+entity+" )...");
		return this.dao.update(entity);
	}
	
	public T save(T entity) {
		logger.info("save( "+entity+" )...");
		if( ((BaseModel)entity).getId() == null ){
			entity = this.dao.insert( entity );
		}else{
			entity = this.dao.update(entity);
		}
		return entity;
	}	
	
	public void delete(Class<T> clazz, Long id){
		logger.info("delete("+id+")...");
		this.dao.delete(clazz, id);
	}
	
	public T findById(Class<T> clazz, Long id){
		logger.info("findById("+id+")...");
		return this.dao.findById(clazz, id);
	}
	
	public T getMaxId(Class<T> clazz){
		logger.info("getMaxId()...");
		return this.dao.getMaxId(clazz);
	}

	public T getMinId(Class<T> clazz){
		logger.info("getMinId()...");
		return this.dao.getMinId(clazz);
	}
	
}
