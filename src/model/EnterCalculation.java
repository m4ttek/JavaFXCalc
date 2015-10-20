package model;

import java.util.Optional;

/**
 * Obliczenie typu żądanie wyniku.
 * 
 * @author Mateusz Kamiński
 */
public class EnterCalculation implements Calculation {

	@Override
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError {
		if ("=".equals(mnemonic)) {
			// jeśli operacja i bufor jest dostępny to wartość przepisywana jest do drugiej liczby
			if (calculatorBean.getOperation().isPresent() && calculatorBean.getBuffer().isPresent()) {
				calculatorBean.setSecondNumber(Optional.of(Double.valueOf(calculatorBean.getBuffer().get().toString())));
			}
			
			// operacja zawsze czyści bufor
			calculatorBean.setBuffer(Optional.empty());
			
			// jeżeli dostępna jest operacja do wykonania, ale drugiej liczby nie ma, powtarzana jest pierwsza z liczb
			if (calculatorBean.getOperation().isPresent() && !calculatorBean.getSecondNumber().isPresent()) {
				calculatorBean.setSecondNumber(calculatorBean.getFirstNumber());
			}
			
			// jeżeli dwie liczby znajdują się w pamięci to wykonujemy na nich operację
			if (calculatorBean.getFirstNumber().isPresent() && calculatorBean.getSecondNumber().isPresent()) {
				Double result = calculatorBean.getOperation().get().apply(calculatorBean.getFirstNumber().get(),
																		  calculatorBean.getSecondNumber().get());
				if (Double.isNaN(result)) {
					throw new CalculationError();
				}
				calculatorBean.setFirstNumber(Optional.of(result));
				return CalculatorUtils.formatNumber(result);
			}
		}
		return null;
	}

}
