package br.com.fico.restcontrollers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fico.models.Launch;
import br.com.fico.models.LaunchType;
import br.com.fico.services.LaunchService;

@RestController
@RequestMapping("/api/launch")
public class LaunchRestController {

	private LaunchService launchService;

	@Autowired
	public void setLancamentoService(LaunchService launchService) {
		this.launchService = launchService;
	}

	@RequestMapping
	public List<Launch> get() {
		System.out.println("get( )");
		
		Calendar firstDayMonthActual = Calendar.getInstance();
		firstDayMonthActual.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar lastDayMonthActual = Calendar.getInstance();
		lastDayMonthActual.set(Calendar.DAY_OF_MONTH, lastDayMonthActual.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return launchService.findByMaturityDateBetween(firstDayMonthActual, lastDayMonthActual);
	}

	@RequestMapping(value="/type/{type}")
	public List<Launch> getByType(@PathVariable("type") LaunchType type) {
		System.out.println("get( )");
		return launchService.findByType( type );
	}
	
	@RequestMapping(value="/lates")
	public List<Launch> getByLate() {
		System.out.println("getByLate( )");
		return launchService.findByLate();
	}
	
	@RequestMapping(value="/lates/perc")
	public Float getByLatePerc() {
		System.out.println("getByLatePerc( )");
		return launchService.getByLatePerc();
	}
	
	@RequestMapping(value="/done/perc")
	public Float getByDonePerc() {
		System.out.println("getByDonePerc( )");
		return launchService.getByDonePerc();
	}
	
	@RequestMapping(value="/waiting/perc")
	public Float getByWaitingPerc() {
		System.out.println("getByWaitingPerc( )");
		return launchService.getByWaitingPerc();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Launch create(@RequestBody Launch launch) {
		System.out.println("post( " + launch + " )");
		return launchService.save(launch);
	}

	@RequestMapping(value = "/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		System.out.println("delete( " + id + " )");
		HttpStatus statusCode = HttpStatus.OK;
		try {
			this.launchService.delete(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(statusCode);
	}

	@RequestMapping(value = "/{id}")
	public Launch get(@PathVariable Long id) {
		System.out.println("get( " + id + " )");
		return this.launchService.findOne(id);
	}
	
	@RequestMapping(value = "/pay/{id}")
	public ResponseEntity<String> pay(@PathVariable Long id) {
		System.out.println("pay( " + id + " )");
		HttpStatus statusCode = HttpStatus.OK;
		try {
			this.launchService.pay(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(statusCode);
	}

}
