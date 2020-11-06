package br.com.controleaula.framework;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BaseDAO<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<T> listAll(Class<T> clazz) {
		logger.info("listAll("+clazz.getName()+")");
		return this.entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
	}

	public T findById(Class<T> clazz, Long id){
		logger.info("findById("+clazz.getName()+", "+id+")");
		return this.entityManager.find(clazz, id);
	}

	@Transactional
	public T insert(T entity) {
		logger.info("insert("+entity+")");
		this.entityManager.persist(entity);
		return entity;
	}

	@Transactional
	public T update(T entity) {
		logger.info("update("+entity+")");
		this.entityManager.merge(entity);
		return entity;
	}
	
	@Transactional
	public void delete(Class<T> clazz, Long id){
		logger.info("delete("+clazz.getName()+", "+id+")");
		T entity = this.findById(clazz, id);
		this.entityManager.remove(entity);
	}
	
	public T getMaxId(Class<T> clazz){
		logger.info("getMaxId("+clazz.getName()+")");
		T entity = this.entityManager.createQuery("SELECT e FROM "+clazz.getName()+
				" e WHERE e.id = (SELECT MAX(e.id) FROM "+clazz.getName()+" e)", clazz).getSingleResult();
		return entity;
	}

	public T getMinId(Class<T> clazz){
		logger.info("getMinId("+clazz.getName()+")");
		T entity = this.entityManager.createQuery("SELECT e FROM "+clazz.getName()+
				" e WHERE e.id = (SELECT MIN(e.id) FROM "+clazz.getName()+" e)", clazz).getSingleResult();
		return entity;
	}

}
