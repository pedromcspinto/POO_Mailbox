package Message;

import java.time.LocalDate;

public interface InterfaceMessage {

	String getEmail();
	String getTopic();
	String getText();
	LocalDate getDate();
	int compareToMessage(InterfaceMessage m);
	
}