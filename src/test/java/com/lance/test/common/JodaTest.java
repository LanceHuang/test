package com.lance.test.common;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class JodaTest {

	@Test
	public void test() {
		DateTime dateTime = new DateTime();
		System.out.println(dateTime.toString());
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");
		DateTimeFormatter franceDtf = dtf.withLocale(Locale.FRANCE);
		
		System.out.println(dateTime.toString(franceDtf));
	}

}
