package ui;

import java.util.ArrayList;


public class InterfaceConsole {

	
	public InterfaceConsole() {
	
	}
	
	public static void displayMenu() {
		System.out.println("Choose input :");
		System.out.println("0 - abort");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create computer");
		System.out.println("5 - Update computer");
		System.out.println("6 - Delete computer");
	}
	
	public static void displayList(ArrayList<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
	}

	
}
