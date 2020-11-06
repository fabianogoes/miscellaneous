package br.com.fico;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = FicoApplication.class)
//@WebAppConfiguration
public class MathTest {

//	@Test
//	public void contextLoads() {
//	}
	
	@Test
	public void testBigDecimalFormatSemPontoComVirgulaDecimal() throws ParseException{
		 String formatted = "1.000,50";
		 
         Locale ptBr = new Locale("pt", "BR");
         DecimalFormat df = (DecimalFormat) NumberFormat.getInstance( ptBr );
         df.setParseBigDecimal(true);
         BigDecimal bd = (BigDecimal) df.parseObject(formatted);
         System.out.println( bd );
         
         Assert.assertNotNull("bigDecimal não deve estar nulo", bd);
         Assert.assertEquals("Resultado esperado deve ser [1000.50]", "1000.50", bd.toString());
	}

}
