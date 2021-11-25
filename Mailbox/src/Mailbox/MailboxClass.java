package Mailbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import Message.InterfaceMessage;

public class MailboxClass {
	private static final int DEFAULT = 200;
	private Map<String, List<InterfaceMessage>>ByEmail;
	private SortedMap<String, List<InterfaceMessage>>topic;
	private SortedSet<InterfaceMessage> CompareByDateReceived;
	private SortedSet<InterfaceMessage> CompareByDateSent;

	public MailboxClass(){
		this.topic= new TreeMap<>();
		this.ByEmail= new HashMap<>(DEFAULT);
		CompareByDateReceived= new TreeSet<>(new Comparators.CompareByDate());	
		CompareByDateSent = new TreeSet<>(new Comparators.CompareByDate());
	}	

	public void sendMessage(InterfaceMessage m, String email, String topicz) {	
		List<InterfaceMessage> message = topic.get(topicz);
		List<InterfaceMessage> byemail = ByEmail.get(email);
		if(message == null) {
			message = new ArrayList<>();
			topic.put(topicz, message);
		}
		if(byemail == null) {
			byemail = new ArrayList<>();
			ByEmail.put(email, byemail);
		}
		byemail.add(m);
		message.add(m);
		CompareByDateSent.add(m);
	}	

	public void receiveMessage(InterfaceMessage m, String email, String topicz){

		List<InterfaceMessage> message = topic.get(topicz);
		List<InterfaceMessage> byemail = ByEmail.get(email);	
		if(message == null) {
			message = new ArrayList<>();
			topic.put(topicz, message);
		}	
		if(byemail == null) {
			byemail = new ArrayList<>();
			ByEmail.put(email, byemail);
		}	
		byemail.add(m);
		message.add(m);
		CompareByDateReceived.add(m);
	}

	public Iterator<InterfaceMessage> listByDateSent() {
		return CompareByDateSent.iterator();
	}

	public Iterator<InterfaceMessage> listByDateReceived() {
		return CompareByDateReceived.iterator();
	}

	public Iterator<InterfaceMessage> ListByTopic(String topicz) {
		List<InterfaceMessage> set = topic.get(topicz);
		return set.iterator();
	}

	public Iterator<InterfaceMessage> listByEmail(String email) {
		List<InterfaceMessage> set = ByEmail.get(email);
		return set.iterator();
	}

	public Iterator<String> listAssuntos() {
		return topic.keySet().iterator();
	}

	public boolean existTopic(String topicz) {
		boolean message = topic.containsKey(topicz);
		return message;
	}

	public boolean existEmail(String email) {
		boolean byemail = ByEmail.containsKey(email);
		return byemail;
	}
	public int compare2message(InterfaceMessage a1, InterfaceMessage a2) {
		int result = -1;
		if(a1.compareToMessage(a2)>0) {
			result = 1;
			
		}
		return result;
	}
	public int isEquals(InterfaceMessage m) {
		Iterator<InterfaceMessage> it = listByDateSent();
		int result =-1;
		while(it.hasNext()) {
			if(compare2message(it.next(), m)>0){
				result = 1;
				break;
			}
			
		}
		return result;
	}
}
