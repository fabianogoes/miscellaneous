package br.com.fico.builders;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.fico.models.Category;
import br.com.fico.models.Launch;
import br.com.fico.models.LaunchType;

public class LaunchBuilder {

	private Launch launch = new Launch("Test "+Math.random(), new BigDecimal(100), Calendar.getInstance(), LaunchType.PAYMENT);
	
	public Launch build(){
		return this.launch;
	}
	
	public LaunchBuilder withId(Long id){
		this.launch.setId(id);
		return this;
	}
	
	public LaunchBuilder withDescription(String description){
		this.launch.setDescription(description);
		return this;
	}
	
	public LaunchBuilder withAmount(BigDecimal amount){
		this.launch.setAmount(amount);
		return this;
	}
	
	public LaunchBuilder withCreatedDate(Calendar createdDate){
		this.launch.setCreatedDate(createdDate);
		return this;
	}
	
	public LaunchBuilder withDoneDate(Calendar doneDate){
		this.launch.setDoneDate(doneDate);
		return this;
	}
	
	public LaunchBuilder withMaturityDate(Calendar maturityDate){
		this.launch.setMaturityDate(maturityDate);
		return this;		
	}
	
	public LaunchBuilder withType(LaunchType type){
		this.launch.setType(type);
		return this;
	}
	
	public LaunchBuilder withDone(Boolean done){
		this.launch.setDone(done);
		return this;
	}
	
	public LaunchBuilder withCategory(Category category){
		this.launch.setCategory(category);
		return this;
	}
	
	public LaunchBuilder withLate(Boolean late){
		this.launch.setLate(late);
		return this;
	}
}
