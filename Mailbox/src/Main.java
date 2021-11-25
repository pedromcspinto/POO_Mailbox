import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

import Exceptions.DuplicateMessageException;
import Exceptions.InexistentEmailException;
import Exceptions.InexistentTopicException;
import Mailbox.MailboxClass;
import Message.InterfaceMessage;
import Message.MessageClass;


public class Main {

	private static enum Command {
		enviar, receber, enviadas, recebidas, assunto, email, assuntos, sair, desconhecido

	}

	private static final String REGISTED = "Mensagem registada.";
	private static final String SPACE = " | ";
	private static final String HEADLINE = "data | assunto | email";
	private static final String HEADLINE2 = "data | assunto | email | texto";

	public static void main(String[] args) {   
		Scanner in = new Scanner(System.in);
		MailboxClass Mbox = new MailboxClass();
		Command comm = getCommand(in);

		while (!comm.equals(Command.sair)){
			switch (comm) {

			case enviar:
				enviaMensagem(Mbox, in);
				break;

			case receber:
				recebeMensagem(Mbox, in);
				break;

			case  enviadas: 
				ListaEnviadas(Mbox);
				break;

			case recebidas:
				ListaRecebidas(Mbox);
				break;

			case assunto:
				ListaAssunto(Mbox, in);
				break;

			case email:
				ListaEmail(Mbox, in);
				break;

			case assuntos:
				ListaAssuntos(Mbox);
				break;

			case sair:
				break;

			default:
				System.out.println("ERRO");

			}
			comm = getCommand(in);
			System.out.println();
		}
		System.out.println("A terminar.");
		System.out.println();
		in.close();
	}

	private static void enviaMensagem(MailboxClass m, Scanner in) {
		try {
		String topic = in.nextLine();
		String email = in.nextLine();
		String text = in.nextLine();
		String date = in.nextLine();
		LocalDate localDate = LocalDate.parse(date);

		MessageClass sms = new MessageClass(email, topic, text, localDate);
		if(m.isEquals(sms)>0) {
			throw new DuplicateMessageException();
		}
		m.sendMessage(sms, email, topic);
		System.out.println(REGISTED);
		}
		catch(DuplicateMessageException e) {
			System.out.println(e.getMessage());
			
		}
	}	

	private static void recebeMensagem(MailboxClass m, Scanner in) {
		
		String topic = in.nextLine();
		String email = in.nextLine();
		String text = in.nextLine();
		String date = in.nextLine();
		LocalDate localDate = LocalDate.parse(date);
		
		MessageClass sms = new MessageClass(email, topic, text, localDate);
		
		m.receiveMessage(sms, email, topic);
		System.out.println(REGISTED);

	}


	private static void ListaEnviadas(MailboxClass m) {
		Iterator<InterfaceMessage> it = m.listByDateSent();
		System.out.println(HEADLINE);
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	private static void ListaRecebidas(MailboxClass m) {
		Iterator<InterfaceMessage> it = m.listByDateReceived();
		System.out.println(HEADLINE);
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	private static void ListaAssunto(MailboxClass m, Scanner in) {
		try {
			String topic = in.nextLine();
			if(!m.existTopic(topic)) {
				throw new InexistentTopicException();
			}
			System.out.println(HEADLINE2);
			Iterator<InterfaceMessage> it = m.ListByTopic(topic);
			while(it.hasNext()) {
				InterfaceMessage o = it.next();
				String text = o.getText();
				System.out.println(o+SPACE+text);	
			}
		}
		catch(InexistentTopicException e) {
			System.out.println(e.getMessage());
		}	
	}

	private static void ListaEmail(MailboxClass m, Scanner in) {
		try {
			String email = in.nextLine();
			if(!m.existEmail(email)) {
				throw new InexistentEmailException();
			}
			System.out.println(HEADLINE2);
			Iterator<InterfaceMessage> it = m.listByEmail(email);
			while(it.hasNext()) {
				InterfaceMessage o = it.next();
				String text = o.getText();
				System.out.println(o+SPACE+text);
			}
		}
		catch(InexistentEmailException e) {
			System.out.println(e.getMessage());	
		}
	}

	private static void ListaAssuntos(MailboxClass m) {
		Iterator<String> it = m.listAssuntos();
		while(it.hasNext()) {
			System.out.println(it.next());

		}
	}

	private static boolean belongsToCommand(String comm) {
		for (Command c : Command.values()) {
			if (c.name().equals(comm)) {
				return true;
			}
		}
		return false;
	}

	private static Command getCommand(Scanner in) {

		String comm = in.nextLine().toLowerCase();
		if (!belongsToCommand(comm)) {
			return Command.desconhecido;
		} else {
			return Command.valueOf(comm);
		}
	}
}
