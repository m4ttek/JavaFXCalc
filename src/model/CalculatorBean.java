package model;

import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Bean reprezentujący wewnętrzny stan kalkulatora.
 * 
 * @author Mateusz Kamiński
 */
public class CalculatorBean {
	
	/**
	 * Bufor na wprowadzane znaki.
	 */
	private Optional<StringBuilder> buffer = Optional.empty(); 

	private Optional<Double> firstNumber = Optional.empty();
	
	private Optional<Double> secondNumber = Optional.empty();
	
	private boolean isBlocked;
	
	/**
	 * Operacja do wykonania (wybrana przez użytkownika)
	 */
	private Optional<BinaryOperator<Double>> operation = Optional.empty();

	public Optional<Double> getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(Optional<Double> firstNumber) {
		this.firstNumber = firstNumber;
	}

	public Optional<Double> getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(Optional<Double> secondNumber) {
		this.secondNumber = secondNumber;
	}

	public Optional<BinaryOperator<Double>> getOperation() {
		return operation;
	}

	public void setOperation(Optional<BinaryOperator<Double>> operation) {
		this.operation = operation;
	}

	public Optional<StringBuilder> getBuffer() {
		return buffer;
	}

	public void setBuffer(Optional<StringBuilder> buffer) {
		this.buffer = buffer;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

}
