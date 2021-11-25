package Message;

import java.time.LocalDate;


public class MessageClass implements InterfaceMessage, Comparable<InterfaceMessage>{

	private static final String SPACE = " | ";
	private String email;
	private String topic;
	private String text;
	private LocalDate date;


	public MessageClass(String email, String topic, String text, LocalDate date){
		this.email=email;
		this.topic=topic;
		this.text=text;
		this.date=date;

	}

	public String getEmail() {
		return email;

	}


	public String getTopic() {
		return topic;

	}

	public String getText() {
		return text;

	}

	public LocalDate getDate() {
		return date;

	}

	public String toString() {
		return getDate()+SPACE+getTopic()+SPACE+getEmail();

	}

	@Override
	public int compareTo(InterfaceMessage m) {
		int compare = this.getEmail().compareTo(m.getEmail());
		if (compare == 0) {
			this.getText().compareTo(m.getText());
			
		} 
		if(compare==0) {
			this.getTopic().compareTo(m.getTopic());
			
		}
		return compare;
	}

	public int compareToMessage(InterfaceMessage m) {
		int result = -1;
		if(this.getEmail().equals(m.getEmail()) && this.getTopic().equals(m.getTopic()) && this.getDate().compareTo(m.getDate())==0){
			result=1;
			
		}
		return result;
	}
}
