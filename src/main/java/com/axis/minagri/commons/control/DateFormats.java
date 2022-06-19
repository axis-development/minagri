/*
 * Copyright 2003-2007 LCM-ANMC, Inc. All rights reserved. This source code is
 * the property of LCM-ANMC, Direction Informatique and cannot be copied or
 * distributed without the formal permission of LCM-ANMC.
 */
package com.axis.minagri.commons.control;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Contains constant SimpleDateFormats.
 *
 * @author t51sagbdz Bert Depaz
 * @version %PR%
 * @date 28-sep-2009
 */
public class DateFormats {
	public static final DateFormats DEFAULT = new DateFormats("ddMMyyyy");
	public static final DateFormats DDMMYYHHMMSS = new DateFormats("ddMMyyHHmmss");
	public static final DateFormats HHMM = new DateFormats("HHmm");
	public static final DateFormats HHMM_WITHOUT_SS = new DateFormats("HH:mm");
	public static final DateFormats HHMMSS = new DateFormats("HHmmss");
	public static final DateFormats HHMMSS_COLON = new DateFormats("HH:mm:ss");
	public static final DateFormats YY = new DateFormats("yy");
	public static final DateFormats YYMMDD = new DateFormats("yyMMdd");
	public static final DateFormats YYYY = new DateFormats("yyyy");
	public static final DateFormats YYYYMM = new DateFormats("yyyyMM");
	public static final DateFormats YYYYMMDD = new DateFormats("yyyyMMdd");
	public static final DateFormats YYYYMMDDHHMM = new DateFormats("yyyyMMddHHmm");
	public static final DateFormats YYYYMMDDHHMMSS = new DateFormats("yyyyMMddHHmmss");
	public static final DateFormats YYYYMMDDHHMMSSMS = new DateFormats("yyyyMMddHHmmss.SSS");
	public static final DateFormats YYYYMMDD_SLASHED = new DateFormats("yyyy/MM/dd");
	public static final DateFormats YYYYMMDD_DASHED = new DateFormats("yyyy-MM-dd");
	public static final DateFormats YYYYMMDD_DASHED_HHMMSS = new DateFormats("yyyy-MM-dd HH:mm:ss");
	public static final DateFormats YYYYMMDD_DASHED_T_HHMMSS = new DateFormats("yyyy-MM-dd'T'HH:mm:ss");
	public static final DateFormats YYYYMMDD_DASHED_HHMMSSSSS = new DateFormats("yyyy-MM-dd HH:mm:ss:SSS");
	public static final DateFormats YYYYMMDD_DASHED_HHMM_A_Z = new DateFormats("yyyy-MM-dd HH:mm a z");
	public static final DateFormats MMYYYY = new DateFormats("MMyyyy");
	public static final DateFormats DDMMYYYY = new DateFormats("ddMMyyyy");
	public static final DateFormats DDMMYYYY_SLASHED = new DateFormats("dd/MM/yyyy");
	public static final DateFormats DDMMYYYY_DASHED = new DateFormats("dd-MM-yyyy");
	public static final DateFormats DDMMYYYY_DASHED_HHMMSS = new DateFormats("dd-MM-yyyy HH:mm:ss");
	public static final DateFormats DDMMYYYY_DASHED_HHMM = new DateFormats("dd-MM-yyyy HH:mm");
	public static final DateFormats YYMMDDHHMMSS = new DateFormats("yyMMddHHmmss");
	public static final DateFormats DDMMYY = new DateFormats("ddMMyy");
	public static final DateFormats DDMM = new DateFormats("ddMM");
	public static final DateFormats DDMMYY_DASHED = new DateFormats("dd-MM-yy");

	private final String dateFormat;
	
	/**
	 * Constructor.
	 */
	private DateFormats(final String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public String getDateFormat() {
		return this.dateFormat;
	}
	
	/**
	 * Formats the given <code>Date</code> into a date/time string.
	 *
	 * @param date
	 *            the date-time value to be formatted into a date-time string.
	 * @return the formatted date-time string.
	 */
	public String format(final Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(this.dateFormat).format(date);
	}
	
	public String format(final LocalDate date) {
		return date == null ? EMPTY : this.format(Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	public String format(final LocalTime date) {
		return date == null ? EMPTY : this.format(Date.from(LocalDateTime.of(LocalDate.now(), date).atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	public String format(final LocalDateTime date) {
		return date == null ? EMPTY : this.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	/**
	 * Parses text from a string to produce a <code>Date</code>.
	 *
	 * @param source
	 *            A <code>String</code>, part of which should be parsed.
	 * @return A <code>Date</code> parsed from the string. In case of error,
	 *         returns null.
	 * @throws ParseException
	 *             in case of format error.
	 */
	public Date parse(final String source) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
		simpleDateFormat.setLenient(false);
		try {
			return simpleDateFormat.parse(source);
		}
		catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Parses text from a string to produce a Date, or null if invalid.
	 */
	public Date parseSafe(final String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
		simpleDateFormat.setLenient(false);
		try {
			return simpleDateFormat.parse(source);
		}
		catch (final ParseException e) {
			return null;
		}
	}
	
	/**
	 * Parses text from a string to produce a Date, or null if invalid.
	 */
	public Date parseIfNotBlank(final String source) {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
		simpleDateFormat.setLenient(false);
		try {
			return simpleDateFormat.parse(source);
		}
		catch (final ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Parses text from a string to produce a <code>Date</code>.
	 *
	 * @param source
	 *            A <code>String</code>, part of which should be parsed.
	 * @param parsePosition
	 *            A <code>ParsePosition</code> object with index and error index
	 *            information as described above.
	 * @return A <code>Date</code> parsed from the string. In case of error,
	 *         returns null.
	 */
	public Date parse(final String source, final ParsePosition parsePosition) {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
		simpleDateFormat.setLenient(false);
		return simpleDateFormat.parse(source, parsePosition);
	}
}