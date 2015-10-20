package model;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.function.IntPredicate;

/**
 * Zawiera wspólne, użyteczne funkcje.
 * 
 * @author Mateusz Kamiński
 */
public class CalculatorUtils {

	public static final long MAX_NUMBER_OF_DIGITS = 16;
	
	/**
	 * Czy wyświetlacz posiada maksymalną liczbę cyfr.
	 * 
	 * @param result wyświetlany rezultat
	 * @return
	 */
	public static boolean hasMaxNumberOfDigits(String result) {
		if (result != null) {
			return result.chars().filter(new IntPredicate() {
				@Override
				public boolean test(int value) {
					return value >= '0' && value <= '9';
				}
			}).count() >= MAX_NUMBER_OF_DIGITS;
		}
		return false;
	}

	/**
	 * Formatuje liczbę, aby pokazać ją na ekranie.
	 * 
	 * @param number
	 * @return
	 */
	public static String formatNumber(double number) {
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.FRANCE);
		decimalFormat.setMaximumFractionDigits((int) (MAX_NUMBER_OF_DIGITS - 1));
		String formatted = decimalFormat.format(number);
		if (hasMaxNumberOfDigits(formatted.replaceAll(" ", ""))) {
			return new DecimalFormat("0.############E0").format(number);
		}
		return formatted.replace(",", ".");
	}
	
}
