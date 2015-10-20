package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Przeprowadza matematyczną kalkulację.
 *  
 * @author Mateusz Kamiński
 */
public class SimpleMathematicalCalculation implements Calculation {

	public static Map<String, BinaryOperator<Double>> MATH_OPERATIONS = new HashMap<>();
	static {
		MATH_OPERATIONS.put("+", new BinaryOperator<Double>() {
			@Override
			public Double apply(Double t, Double u) {
				return t + u;
			}
		});
		MATH_OPERATIONS.put("-", new BinaryOperator<Double>() {
			@Override
			public Double apply(Double t, Double u) {
				return t - u;
			}
		});
		MATH_OPERATIONS.put("/", new BinaryOperator<Double>() {
			@Override
			public Double apply(Double t, Double u) {
				return t / u;
			}
		});
		MATH_OPERATIONS.put("*", new BinaryOperator<Double>() {
			@Override
			public Double apply(Double t, Double u) {
				return t * u;
			}
		});
	}
	
	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if (MATH_OPERATIONS.containsKey(mnemonic)) {
			Double newValue = null;
			if (calculatorBean.getBuffer().isPresent()) {
				newValue = Double.valueOf(calculatorBean.getBuffer().get().toString());
				if (calculatorBean.getFirstNumber().isPresent()) {
					calculatorBean.setSecondNumber(Optional.of(newValue));
				} else {
					calculatorBean.setFirstNumber(Optional.of(newValue));
				}
				calculatorBean.setBuffer(Optional.empty());
			}
			if (calculatorBean.getOperation().isPresent() && calculatorBean.getSecondNumber().isPresent()) {
				newValue = calculatorBean.getOperation().get().apply(calculatorBean.getFirstNumber().get(),
						   calculatorBean.getSecondNumber().get());
				if (!Double.isFinite(newValue)) {
					throw new CalculationError();
				}
				calculatorBean.setFirstNumber(Optional.of(newValue));
				calculatorBean.setSecondNumber(Optional.empty());
			}
			calculatorBean.setOperation(Optional.of(MATH_OPERATIONS.get(mnemonic)));
			if (newValue != null) {
				return CalculatorUtils.formatNumber(newValue);
			}
		}
		return null;
	}

}
