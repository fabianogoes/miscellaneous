package br.com.fico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fico.models.Bank;
import br.com.fico.repositories.BankRepository;

@Service
public class BankService {

	private BankRepository bankRepository;

	@Autowired
	public void setBankRepository(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public Bank save(Bank bank) {
		try{
			return this.bankRepository.save(bank);
		}catch(Exception ex){
			System.out.println( ex.getMessage() );
			if( ex.getMessage().contains( "constraint [UQ_BANK_CODE]" ) ){
				throw new RuntimeException( "Código de Banco ["+bank.getCode()+" ] já existe!" );
			}
			if( ex.getMessage().contains( "constraint [UQ_BANK_NAME]" ) ){
				throw new RuntimeException( "Nome de Banco ["+bank.getName()+" ] já existe!" );
			}
		}
		return null;
	}

	public List<Bank> findAll() {
		return (List<Bank>) this.bankRepository.findAll();
	}

}
