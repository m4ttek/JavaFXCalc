package model;

import java.util.Optional;

/**
 * Obliczenie typu zmiana znaku.
 * 
 * @author Mateusz Kamiński
 */
public class ChangeSingCalculation implements Calculation {

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if (!"+/-".equals(mnemonic)) {
			return null;
		}
		// jeżeli bufor jest dostępny to zmieniamy w nim pierwszy znak
		if (calculatorBean.getBuffer().isPresent()) {
			StringBuilder buffer = calculatorBean.getBuffer().get();
			if (buffer.charAt(0) == '-') {
				buffer.deleteCharAt(0);
			} else {
				buffer.insert(0, '-');
			}
			return buffer.toString();
			
			// jeżeli w pamięci istnieje już jakaś liczba to zmieniamy jej znak
		} else if (calculatorBean.getFirstNumber().isPresent()) {
			Double value = calculatorBean.getFirstNumber().get();
			if (Double.isNaN(value)) {
				throw new CalculationError();
			}
			calculatorBean.setFirstNumber(Optional.of(-value));
			return CalculatorUtils.formatNumber(-value);
		}
 		return null;
	}

}
