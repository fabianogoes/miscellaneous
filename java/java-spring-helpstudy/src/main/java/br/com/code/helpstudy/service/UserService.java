package br.com.code.helpstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.code.helpstudy.dao.UserDAOImpl;
import br.com.code.helpstudy.model.User;

@Service("userService")
public class UserService {

	@Autowired
	private UserDAOImpl userDAOImpl;
	
	public UserDAOImpl getUserDAOImpl() {
		return userDAOImpl;
	}

	public void setUserDAOImpl(UserDAOImpl userDAOImpl) {
		this.userDAOImpl = userDAOImpl;
	}

	public void persist(User entity) {
		this.userDAOImpl.persist(entity);
	}
	
	public List<User> getAll(){
		return this.userDAOImpl.getAll(User.class);
	}
	
	public User findById(Long id){
		return this.userDAOImpl.findById(User.class, id);
	}	
}
