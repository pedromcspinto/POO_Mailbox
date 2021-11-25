package Exceptions;

@SuppressWarnings("serial")
public class IllegalArgumentException extends Exception {
	
	public static final String MESSAGE = "Opcao inexistente.";
	
	public IllegalArgumentException() {
		super(MESSAGE);
	}
}