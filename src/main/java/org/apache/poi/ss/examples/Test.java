package org.apache.poi.ss.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Test {
	private static SimpleDateFormat fmt = new SimpleDateFormat("MMM-dd");

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(fmt.parse("Jul-9"));
	}

}
