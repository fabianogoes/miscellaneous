package br.com.fico;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class DateTest {

	@Test
	public void testLastDayMonth(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, Calendar.MAY);
	    int lastDayMay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    Assert.assertEquals("Last day/may should be [31]", 31, lastDayMay);
	}
	
}
