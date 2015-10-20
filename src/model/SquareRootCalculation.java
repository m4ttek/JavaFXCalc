package model;

import java.util.Optional;

/**
 * Specjalna operacja matematyczna - pierwiastek.
 * 
 * @author Mateusz Kamiński
 */
public class SquareRootCalculation implements Calculation {

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if ("√".equals(mnemonic)) {
			Double value;
			if (calculatorBean.getBuffer().isPresent()) {
				StringBuilder buffer = calculatorBean.getBuffer().get();
				value = Double.valueOf(buffer.toString());
				value = Math.sqrt(Double.valueOf(value));
				calculatorBean.setBuffer(Optional.empty());
			} else if (calculatorBean.getFirstNumber().isPresent()) {
				value = calculatorBean.getFirstNumber().get();
			} else {
				value = 0.0;
			}
			value = Math.sqrt(Double.valueOf(value));
			if (Double.isNaN(value)) {
				throw new CalculationError();
			}
			calculatorBean.setFirstNumber(Optional.of(value));
			return CalculatorUtils.formatNumber(value);
		}
		return null;
	}

}
