package controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputControler {

	public static Integer getInputInt() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		Integer inputInt = null;
		System.out.println("Enter a number");
		try {
			inputInt = Integer.parseInt(bufferReader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Error format, null will be taken");
		} catch (IOException e) {
			System.out.println("Error IOstream, null will be taken");
		}
		return inputInt;
	
	}

	public static Long getInputLong() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		Long inputInt = null;
		System.out.println("Enter a number");
		try {
			inputInt = Long.parseLong(bufferReader.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Error format, null will be taken");
		} catch (IOException e) {
			System.out.println("Error IOstream, null will be taken");
		}
		return inputInt;
	
	}

	public static int getInputInt(int min, int max) {
		Integer inputInt;
		do {
			inputInt = getInputInt();
		}while (inputInt == null || 
				inputInt.intValue() < min ||
				inputInt.intValue() > max);
		return inputInt.intValue();
	}
	
	public static String getInputString() {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		System.out.println("Input String");
		try {
			inputString = bufferReader.readLine();
		} catch (IOException e) {
			System.out.println("Error IOstream");
			System.out.println(e.getMessage());
		}
		return inputString;
	}
	
	public static String[] getInputString(int number) {
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputString = null;
		do {
			System.out.println("Input String with "+number+" arguments");
			try {
				inputString = bufferReader.readLine().split(" ");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		while (inputString.length != number);
		return inputString;
	}

	public static String getInputStringNotNull() {
		String inputString = null;
		do {
			inputString = getInputString();
		}while(inputString == null);
		return inputString;
	}

}
