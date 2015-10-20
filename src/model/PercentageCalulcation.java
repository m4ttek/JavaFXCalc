package model;

import java.util.Optional;

/**
 * Wylicza operację procent.
 * 
 * @author Mateusz Kamiński
 */
public class PercentageCalulcation implements Calculation {

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if ("%".equals(mnemonic)) {
			Double value = null;
			if (calculatorBean.getBuffer().isPresent()) {
				value = Double.valueOf(calculatorBean.getBuffer().get().toString());
				calculatorBean.setBuffer(Optional.empty());
			} else if (calculatorBean.getFirstNumber().isPresent()) {
				value = Double.valueOf(calculatorBean.getFirstNumber().get());
			}
			if (value != null) {
				value = value * 0.01;
				if (calculatorBean.getOperation().isPresent()) {
					calculatorBean.setSecondNumber(Optional.of(value));
				} else {
					calculatorBean.setFirstNumber(Optional.of(value));
				}
				return CalculatorUtils.formatNumber(value);
			}
			
		}
		return null;
	}

}
