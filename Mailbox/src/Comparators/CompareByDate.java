package Comparators;

import java.util.Comparator;

import Message.InterfaceMessage;

public class CompareByDate implements Comparator<InterfaceMessage>{

	@Override
	public int compare(InterfaceMessage a1, InterfaceMessage a2) {
		int compare = a1.getDate().compareTo(a2.getDate());
		return compare;
	}
}
