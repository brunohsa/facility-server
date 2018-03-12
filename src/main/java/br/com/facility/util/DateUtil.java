package br.com.facility.util;

import br.com.facility.exceptions.ws.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	private static final String COMPLETE_DATE_FORMATTER_STR = "dd/MM/yyyy";
	private static final String COMPLETE_DATE_AND_HOURS_FORMATTER_STR = "dd/MM/yyyy hh:mm:ss";

	public static final String COMPLETE_DATE_FORMATTER = "dd-MM-yyyy";
	public static final String COMPLETE_DATE_AND_HOURS_FORMATTER = "dd-MM-yyyy hh:mm:ss";

	public static final String COMPLETE_HOURS_FORMATTER = "hh:mm:ss";

	private DateUtil() {
	}

	/**
	 * Retorna a data formatada com as horas
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formatDateTime(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_DATE_AND_HOURS_FORMATTER_STR);
		return dateTime.format(formatter);
	}

	/**
	 * Retorna apenas as horas da data
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formatHours(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_HOURS_FORMATTER);
		return dateTime.format(formatter);
	}

	/**
	 * Retorna a data formatada
	 *
	 * @param date
	 * @return
	 */
	public static String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(COMPLETE_DATE_FORMATTER_STR);
		return date.format(formatter);
	}

	/**
	 * Recupera a data no formato DD/MM/AAAA
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate getLocalDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.COMPLETE_DATE_FORMATTER);
		try {
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormatException();
		}
	}

	/**
	 * Recupera a data no formato DD/MM/AAAA hh:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static LocalDate getLocalDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.COMPLETE_DATE_AND_HOURS_FORMATTER);
		try {
			return LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormatException();
		}
	}
}
