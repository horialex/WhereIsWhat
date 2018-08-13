package com.tools.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

	/*public static String parseDate(String dateString, String initialFormatString, String finalFormatString) {
		DateFormat formatInitial = new SimpleDateFormat(initialFormatString);
		DateFormat formatFinal = new SimpleDateFormat(finalFormatString);
		Date date = new Date();
		try {
			date = formatInitial.parse(dateString);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		return String.valueOf(formatFinal.format(date));

	}*/

	public static String formatStringDate(String dateString,  String formatString) {
		LocalDateTime date = LocalDateTime.parse(dateString);

		return date.format(DateTimeFormatter.ofPattern(formatString));
	}

	public static String formatDate(LocalDateTime date, String format) {

		return date.format(DateTimeFormatter.ofPattern(format));
	}


}
