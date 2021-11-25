package Exceptions;

@SuppressWarnings("serial")
public class InexistentEmailException extends Exception {
	
	public static final String MESSAGE = "Nao existem mensagens trocadas com esse email.";
	
	public InexistentEmailException() {
		super(MESSAGE);
	}
}