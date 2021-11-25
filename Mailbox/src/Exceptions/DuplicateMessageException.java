package Exceptions;

@SuppressWarnings("serial")
public class DuplicateMessageException extends Exception {
	
	public static final String MESSAGE = "Mensagem duplicada.";
	
	public DuplicateMessageException() {
		super(MESSAGE);
	}
}