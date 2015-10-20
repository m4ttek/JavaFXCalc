package model;

import java.util.Optional;

/**
 * Przeprowadza operację wyczyszczenia wyniku.
 * 
 * @author Mateusz Kamiński
 */
public class ClearCalculation implements Calculation {

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic)
			throws CalculationError {
		if ("C".equals(mnemonic)) {
			calculatorBean.setBlocked(false);
			calculatorBean.setBuffer(Optional.empty());
			calculatorBean.setFirstNumber(Optional.empty());
			calculatorBean.setSecondNumber(Optional.empty());
			calculatorBean.setOperation(Optional.empty());
			return "0";
		}
		return null;
	}

}
