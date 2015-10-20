package model;

import java.util.Optional;

/**
 * Zwykłe zachowanie polegające na dodawaniu znaków
 * 
 * @author Mateusz Kamiński
 */
public class DefaultCalculation implements Calculation {
	
	public static final String SIGNS = "01234567890.";

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if (!SIGNS.contains(mnemonic)) {
			return null;
		}
		StringBuilder buffer;
		if (calculatorBean.getBuffer().isPresent()) {
			buffer = calculatorBean.getBuffer().get();
		} else {
			buffer = new StringBuilder();
			calculatorBean.setBuffer(Optional.of(buffer));
			if (calculatorBean.getFirstNumber().isPresent() && !calculatorBean.getOperation().isPresent()) {
				calculatorBean.setFirstNumber(Optional.empty());
			}
		}
		if (!CalculatorUtils.hasMaxNumberOfDigits(buffer.toString())) {
			if (".".equals(mnemonic) && buffer.length() == 0) {
				buffer.append("0").append(mnemonic);
			} else if (!(".".equals(mnemonic) && buffer.toString().contains("."))) {
				buffer.append(mnemonic);
			}
		}
		return buffer.toString();
	}

}
