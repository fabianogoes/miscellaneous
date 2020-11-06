package br.com.code.helpstudy.dao;

import org.springframework.stereotype.Repository;

import br.com.code.helpstudy.model.User;

@Repository("userDAOImpl")
public class UserDAOImpl extends GenericDAO<User> {
}
