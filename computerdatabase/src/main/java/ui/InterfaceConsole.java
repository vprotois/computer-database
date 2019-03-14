package ui;

import java.util.List;



public class InterfaceConsole {

	
	public InterfaceConsole() {
	
	}
	
	public static void displayMenu() {
		System.out.println("Choose input :");
		System.out.println("0 - Abort");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create computer");
		System.out.println("5 - Update computer");
		System.out.println("6 - Delete computer");
		System.out.println("7 - List computers with pages");
		System.out.println("8 - List companies with pages");
	}
	
	public static void displayPageMenu() {
		System.out.println("0 - Abort");
		System.out.println("1 - Previous Page");
		System.out.println("2 - Next Page");
	}
	
	
	public static void display(Object o) {
		System.out.println(o);
	}
	
	public static <T> void displayList(List<T> list) {
		list.forEach(System.out::println);
	}

	
}
