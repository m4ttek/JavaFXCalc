package model;

/**
 * Interfejs dla operacji kalkulacyjnych.
 * 
 * @author Mateusz Kamiński
 */
public interface Calculation {
	
	/**
	 * Przeprowadza operację na stanie kalkulatora.
	 * 
	 * @param calculatorBean przechowuje stan pamięci kalkulatora
	 * @param mnemonic input z kalkulatora
	 * @return co wyświetlić na wyświetlaczu, jeżeli nie dotyczy to null
	 * @throws CalculationError błąd rzucany w przypadku niepoprawnego wyniku
	 */
	public String calculate(CalculatorBean calculatorBean, String mnemonic) throws CalculationError;
}
