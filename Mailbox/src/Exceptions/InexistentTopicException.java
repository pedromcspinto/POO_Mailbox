package Exceptions;

@SuppressWarnings("serial")
public class InexistentTopicException extends Exception {
	
	public static final String MESSAGE = "Nao existem mensagens trocadas com esse assunto.";
	
	public InexistentTopicException() {
		super(MESSAGE);
	}
}