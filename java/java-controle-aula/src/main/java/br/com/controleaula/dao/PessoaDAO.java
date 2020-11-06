package br.com.controleaula.dao;

import org.springframework.stereotype.Repository;

import br.com.controleaula.framework.BaseDAO;
import br.com.controleaula.model.Pessoa;

@Repository("PessoaDAO")
public class PessoaDAO extends BaseDAO<Pessoa> {

}
