package br.com.fico.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fico.models.Launch;
import br.com.fico.models.LaunchType;
import br.com.fico.repositories.LaunchRepository;

@Service
public class LaunchService {

	private LaunchRepository launchRepository;

	@Autowired
	public void setLancamentoRepository(LaunchRepository launchRepository) {
		this.launchRepository = launchRepository;
	}

	public Launch save(Launch launch) {
		Calendar doneDate = launch.getDone() ? Calendar.getInstance() : null;
		launch.setDoneDate(doneDate);		
		return this.launchRepository.save(launch);
	}

	public List<Launch> findAll() {
		return (List<Launch>) this.launchRepository.findAll();
	}

	public void delete(Long id) {
		this.launchRepository.delete(id);
	}

	public Launch findOne(Long id) {
		return this.launchRepository.findOne(id);
	}

	public void pay(Long id) {
		Launch launch = this.findOne(id);
		boolean done = !launch.getDone(); 
		launch.setDone(done);
		Calendar doneDate = done ? Calendar.getInstance() : null;
		launch.setDoneDate(doneDate);
		this.save(launch);
	}

	public List<Launch> findByType(LaunchType type) {
		return this.launchRepository.findByType(type);
	}
	
	public List<Launch> findByLate() {
		Calendar dateNow = Calendar.getInstance();
		return this.launchRepository.findByDoneFalseAndMaturityDateBefore(dateNow);
	}
	
	public List<Launch> findByWaiting() {
		Calendar dateNow = Calendar.getInstance();
		return this.launchRepository.findByDoneFalseAndMaturityDateGreaterThanEqual(dateNow);
	}

	public float getByLatePerc() {
		int latesCount = this.findByLate().size();
		int allCount = this.findAll().size();
		float latePerc = 0;
		if( allCount > 0 ){
			latePerc = (float)((latesCount * 100) / allCount);
		}
		return latePerc;
	}

	public Float getByDonePerc() {
		int doneCount = this.launchRepository.findByDoneTrue().size();
		int allCount = this.findAll().size();
		float latePerc = 0;
		if( allCount > 0){
			latePerc = (float)((doneCount * 100) / allCount);
		}
		return latePerc;
	}

	public Float getByWaitingPerc() {
		int waitingCount = this.findByWaiting().size();
		int allCount = this.findAll().size();
		float latePerc = 0;
		if( allCount > 0 ){
			latePerc = (float)((waitingCount * 100) / allCount);
		}
		return latePerc;
	}

	public List<Launch> findByMaturityDateBetween(Calendar firstDayMonthActual, Calendar lastDayMonthActual) {
		return this.findByMaturityDateBetween(firstDayMonthActual, lastDayMonthActual);
	}

}
