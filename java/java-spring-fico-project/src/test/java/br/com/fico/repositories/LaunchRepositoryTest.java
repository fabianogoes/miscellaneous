package br.com.fico.repositories;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.fico.FicoApplication;
import br.com.fico.builders.CategoryBuilder;
import br.com.fico.builders.LaunchBuilder;
import br.com.fico.models.Category;
import br.com.fico.models.Launch;
import br.com.fico.models.LaunchType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FicoApplication.class)
@WebAppConfiguration
public class LaunchRepositoryTest {

	@Autowired
	private LaunchRepository launchRepository;
	
	@Autowired
	private CategoryRepository categoryRepository; 

	@Test
	public void testFindByTypeWithPAYMENT(){
		Category category = new CategoryBuilder().build();
		this.categoryRepository.save(category);
		Assert.assertTrue("Id [Category] should be greater then zero", category.getId() > 0);
		
		Launch launch = new LaunchBuilder()
				.withType(LaunchType.PAYMENT)
				.withCategory(category)
				.build();
		this.launchRepository.save(launch);
		Assert.assertTrue("Id [Launch] should be greater then zero", category.getId() > 0);
		
		List<Launch> list = launchRepository.findByType( LaunchType.PAYMENT );
		Assert.assertNotNull("List should[PAYMENTs] be not null", list);
		for (Launch l : list) {
			Assert.assertEquals("Type should be [PAYMENT]", LaunchType.PAYMENT, l.getType());
		}
	}
	
	@Test
	public void testFindByTypeWithRECEIPT(){
		Category category = new CategoryBuilder().build();
		this.categoryRepository.save(category);
		Assert.assertTrue("Id [Category] should be greater then zero", category.getId() > 0);
		
		Launch launch = new LaunchBuilder()
				.withType(LaunchType.RECEIPT)
				.withCategory(category)
				.build();
		this.launchRepository.save(launch);
		Assert.assertTrue("Id [Launch] should be greater then zero", launch.getId() > 0);
		
		List<Launch> list = launchRepository.findByType( LaunchType.RECEIPT );
		Assert.assertNotNull("List should[RECEIPTs] be not null", list);
		for (Launch l : list) {
			Assert.assertEquals("Type should be [RECEIPT]", LaunchType.RECEIPT, l.getType());
		}
	}
	
	@Test
	public void testFindByMaturityDateBetween(){
		Category category = new CategoryBuilder().build();
		this.categoryRepository.save(category);
		Assert.assertTrue("Id [Category] should be greater then zero", category.getId() > 0);
		
		Calendar maturityDate = Calendar.getInstance();
		Launch launch = new LaunchBuilder()
				.withType(LaunchType.RECEIPT)
				.withCategory(category)
				.withMaturityDate(maturityDate)
				.build();
		this.launchRepository.save(launch);
		Assert.assertTrue("Id [Launch] should be greater then zero", launch.getId() > 0);
		
		maturityDate = Calendar.getInstance();
		maturityDate.set(Calendar.MONTH, maturityDate.get(Calendar.MONTH)+1);
		launch = new LaunchBuilder()
				.withType(LaunchType.RECEIPT)
				.withCategory(category)
				.withMaturityDate(maturityDate)
				.build();
		this.launchRepository.save(launch);		
		Assert.assertTrue("Id [Launch] should be greater then zero", launch.getId() > 0);
		
		Calendar dateInit = Calendar.getInstance();
		dateInit.set(Calendar.DAY_OF_MONTH, 1);
		Calendar dateEnd = Calendar.getInstance();
		dateEnd.set(Calendar.DAY_OF_MONTH, dateEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
		List<Launch> list = this.launchRepository.findByMaturityDateBetween(dateInit, dateEnd);
		Assert.assertNotNull("List should be not null", list);
	}
	
	
}
