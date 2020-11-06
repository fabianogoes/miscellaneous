package br.com.fico.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fico.models.Bank;
import br.com.fico.services.BankService;

@RestController
@RequestMapping("/api/bank")
public class BankRestController {

	private BankService bankService;

	@Autowired
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	@RequestMapping
	public List<Bank> get() {
		return bankService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Bank create(@RequestBody Bank bank) {
		System.out.println( "post( "+bank+" )" );
		return bankService.save(bank);
	}

}
