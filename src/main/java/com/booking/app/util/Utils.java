package com.booking.app.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

	private static NumberFormat formatter = NumberFormat.getInstance(Locale.US);
	static {
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		formatter.setRoundingMode(RoundingMode.HALF_UP);
	}
	
	/**
	 * Format floatVal to 2 decimal places
	 * @param floatVal
	 * @return
	 */
	public static float formatFloat(float floatVal) {
		return Float.parseFloat(formatter.format(floatVal));
	}
}
