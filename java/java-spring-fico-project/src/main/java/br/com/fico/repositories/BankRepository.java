package br.com.fico.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fico.models.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {

}
