package br.com.facility.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final String COMPLETE_DATE_FORMATTER = "dd/MM/yyyy";
	private static final String COMPLETE_HOURS_FORMATTER = "hh:mm:ss";
	private static final String COMPLETE_DATE_AND_HOURS_FORMATTER = "dd/MM/yyyy hh:mm:ss";

	/**
	 * Retorna a data formatada com as horas
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formattDateTime(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_DATE_AND_HOURS_FORMATTER);
		return dateTime.format(formatter);
	}

	/**
	 * Retorna apenas as horas da data
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formattHours(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_HOURS_FORMATTER);
		return dateTime.format(formatter);
	}

	/**
	 * Retorna a data formatada
	 *
	 * @param date
	 * @return
	 */
	public static String formattDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_DATE_FORMATTER);
		return date.format(formatter);
	}
}