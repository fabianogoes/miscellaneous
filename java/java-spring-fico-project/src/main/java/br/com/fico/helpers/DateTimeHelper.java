package br.com.fico.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeHelper {

	private static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

	public static String toDate(Calendar date) {
		String dateFormatted = "";
		if (date != null) {
			dateFormatted = sdfDate.format(date.getTime());
		}
		return dateFormatted;
	}

}
